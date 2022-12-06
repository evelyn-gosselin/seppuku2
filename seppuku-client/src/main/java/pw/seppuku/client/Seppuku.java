package pw.seppuku.client;

import pw.seppuku.client.feature.persistent.PluginLoaderFeature;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.bus.buses.SimpleEventBus;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.repository.repositories.SimpleFeatureRepository;
import pw.seppuku.plugin.repository.PluginRepository;
import pw.seppuku.plugin.repository.repositories.SimplePluginRepository;

public final class Seppuku {

  private static volatile Seppuku instance = null;

  private final EventBus eventBus = new SimpleEventBus();
  private final FeatureRepository featureRepository = new SimpleFeatureRepository();
  private final PluginRepository pluginRepository = new SimplePluginRepository();

  private Seppuku()
      throws DuplicateUniqueIdentifierFeatureException, CouldNotBeFoundFeatureException {
    final var pluginLoaderFeature = new PluginLoaderFeature(eventBus, featureRepository,
        pluginRepository);
    featureRepository.add(pluginLoaderFeature);
    pluginLoaderFeature.load();

    featureRepository.findFeaturesByClassAndPredicate(PersistentFeature.class, persistentFeature ->
            persistentFeature.uniqueIdentifier() != pluginLoaderFeature.uniqueIdentifier())
        .forEach(PersistentFeature::load);
  }

  public static Seppuku instance() {
    if (instance == null) {
      synchronized (Seppuku.class) {
        if (instance == null) {
          try {
            instance = new Seppuku();
          } catch (final DuplicateUniqueIdentifierFeatureException | CouldNotBeFoundFeatureException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
          }
        }
      }
    }

    return instance;
  }

  public EventBus eventBus() {
    return eventBus;
  }

  public FeatureRepository featureRepository() {
    return featureRepository;
  }

  public PluginRepository pluginRepository() {
    return pluginRepository;
  }
}
