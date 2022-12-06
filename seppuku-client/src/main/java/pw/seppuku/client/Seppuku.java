package pw.seppuku.client;

import pw.seppuku.core.SeppukuCorePlugin;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.bus.buses.SimpleEventBus;
import pw.seppuku.events.SeppukuEventsPlugin;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.repository.repositories.SimpleFeatureRepository;
import pw.seppuku.plugin.exception.exceptions.DuplicateUniqueIdentifierPluginException;
import pw.seppuku.plugin.repository.PluginRepository;
import pw.seppuku.plugin.repository.repositories.SimplePluginRepository;

public final class Seppuku {
    private static volatile Seppuku instance = null;

    private final EventBus eventBus = new SimpleEventBus();
    private final FeatureRepository featureRepository = new SimpleFeatureRepository();
    private final PluginRepository pluginRepository = new SimplePluginRepository();

    private Seppuku() throws DuplicateUniqueIdentifierPluginException, DuplicateUniqueIdentifierFeatureException {
        final var seppukuEventsPlugin = new SeppukuEventsPlugin();
        seppukuEventsPlugin.load(eventBus, featureRepository);
        pluginRepository.add(seppukuEventsPlugin);

        final var seppukuCorePlugin = new SeppukuCorePlugin();
        seppukuCorePlugin.load(eventBus, featureRepository);
        pluginRepository.add(seppukuCorePlugin);
    }

    public static Seppuku instance() throws DuplicateUniqueIdentifierPluginException, DuplicateUniqueIdentifierFeatureException {
        if (instance == null) {
            synchronized (Seppuku.class) {
                if (instance == null) {
                    instance = new Seppuku();
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
