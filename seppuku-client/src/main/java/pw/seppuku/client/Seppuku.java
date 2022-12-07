package pw.seppuku.client;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import pw.seppuku.client.feature.persistent.PluginLoaderFeature;
import pw.seppuku.client.feature.repository.repositories.SimpleFeatureRepository;
import pw.seppuku.client.plugin.repository.repositories.SimplePluginRepository;
import pw.seppuku.client.resolver.resolvers.SimpleResolver;
import pw.seppuku.client.transform.bundle.bundle.SimpleTransformerBundle;
import pw.seppuku.client.transform.transformers.StringToExecutableFeatureTransformer;
import pw.seppuku.client.transform.transformers.StringToFeatureTransformer;
import pw.seppuku.client.transform.transformers.StringToPersistentFeatureTransformer;
import pw.seppuku.client.transform.transformers.StringToToggleableFeatureTransformer;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.bus.buses.SimpleEventBus;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.plugin.repository.PluginRepository;
import pw.seppuku.resolver.Resolver;
import pw.seppuku.transform.Transformer;
import pw.seppuku.transform.bundle.TransformerBundle;

public final class Seppuku {

  private static volatile Seppuku instance = null;

  // @formatter:off
  private final Resolver resolver = new SimpleResolver(Map.of(
      EventBus.class, new SimpleEventBus(),
      FeatureRepository.class, new SimpleFeatureRepository(),
      PluginRepository.class, new SimplePluginRepository(),
      TransformerBundle.class, new SimpleTransformerBundle()
  ));
  // @formatter:on

  private Seppuku()
      throws DuplicateUniqueIdentifierFeatureException, CouldNotBeFoundFeatureException, InvocationTargetException, InstantiationException, IllegalAccessException {
    addTransformersToTransformerBundle();
    addPluginLoaderToFeatureRepository();
    loadPersistentFeatures();
  }

  public static Seppuku instance() {
    if (instance == null) {
      synchronized (Seppuku.class) {
        if (instance == null) {
          try {
            instance = new Seppuku();
          } catch (final DuplicateUniqueIdentifierFeatureException | CouldNotBeFoundFeatureException | InvocationTargetException | InstantiationException | IllegalAccessException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
          }
        }
      }
    }

    return instance;
  }

  private void addTransformersToTransformerBundle()
      throws InvocationTargetException, InstantiationException, IllegalAccessException {
    final var transformerBundle = resolver.resolveDependency(TransformerBundle.class);

    transformerBundle.addAll(Transformer.builtinTransformers());

    // @formatter:off
    transformerBundle.addAll(List.of(
        resolver.create(StringToExecutableFeatureTransformer.class),
        resolver.create(StringToFeatureTransformer.class),
        resolver.create(StringToPersistentFeatureTransformer.class),
        resolver.create(StringToToggleableFeatureTransformer.class)
    ));
    // @formatter:on
  }

  private void addPluginLoaderToFeatureRepository()
      throws InvocationTargetException, InstantiationException, IllegalAccessException, DuplicateUniqueIdentifierFeatureException {
    final var featureRepository = resolver.resolveDependency(FeatureRepository.class);
    final var pluginLoaderFeature = resolver.create(PluginLoaderFeature.class);

    featureRepository.add(pluginLoaderFeature);
    pluginLoaderFeature.load();
  }

  private void loadPersistentFeatures() throws CouldNotBeFoundFeatureException {
    final var featureRepository = resolver.resolveDependency(FeatureRepository.class);
    final var pluginLoader = featureRepository.findFeatureByClass(PluginLoaderFeature.class);

    featureRepository.findFeaturesByClassAndPredicate(PersistentFeature.class,
        persistentFeature -> persistentFeature.uniqueIdentifier()
            != pluginLoader.uniqueIdentifier()).forEach(PersistentFeature::load);
  }

  public Resolver resolver() {
    return resolver;
  }

  public EventBus eventBus() {
    return resolver.resolveDependency(EventBus.class);
  }

  public FeatureRepository featureRepository() {
    return resolver.resolveDependency(FeatureRepository.class);
  }

  public PluginRepository pluginRepository() {
    return resolver.resolveDependency(PluginRepository.class);
  }
}
