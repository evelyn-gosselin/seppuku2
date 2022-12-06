package pw.seppuku.client;

import pw.seppuku.client.di.injectors.SimpleDependencyInjector;
import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.bus.EventBus;
import pw.seppuku.client.event.bus.buses.SimpleEventBus;
import pw.seppuku.client.feature.Feature;
import pw.seppuku.client.feature.registry.FeatureRegistry;
import pw.seppuku.client.feature.registry.registries.SimpleFeatureRegistry;
import pw.seppuku.client.feature.toggleable.features.SprintFeature;

import java.util.Map;

public final class Seppuku {

    private static volatile Seppuku instance = null;

    private final EventBus<Event> eventBus = new SimpleEventBus<>();
    private final FeatureRegistry<Feature> featureRegistry = new SimpleFeatureRegistry<>();

    private Seppuku() throws Exception {
        final var featureDependencyInjector = new SimpleDependencyInjector<Feature>(Map.of(
                EventBus.class, eventBus,
                FeatureRegistry.class, featureRegistry
        ));

        final var sprintFeature = featureDependencyInjector.create(SprintFeature.class);
        featureRegistry.add(sprintFeature);

        sprintFeature.setRunning(true);
    }

    public static Seppuku instance() throws Exception {
        if (instance == null) {
            synchronized (Seppuku.class) {
                if (instance == null) {
                    instance = new Seppuku();
                }
            }
        }

        return instance;
    }

    public EventBus<Event> eventBus() {
        return eventBus;
    }

    public FeatureRegistry<Feature> featureRegistry() {
        return featureRegistry;
    }
}
