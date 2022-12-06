package pw.seppuku.client.feature;

import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.bus.EventBus;
import pw.seppuku.client.feature.registry.FeatureRegistry;

import java.util.UUID;

public abstract class AbstractFeature implements Feature {

    private final UUID uniqueIdentifier;

    private final String humanIdentifier;

    private final String description;

    private final String author;

    private final EventBus<Event> eventBus;

    private final FeatureRegistry<Feature> featureRegistry;

    protected AbstractFeature(final UUID uniqueIdentifier, final String humanIdentifier, final String description, final String author, final EventBus<Event> eventBus, final FeatureRegistry<Feature> featureRegistry) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.humanIdentifier = humanIdentifier;
        this.description = description;
        this.author = author;
        this.eventBus = eventBus;
        this.featureRegistry = featureRegistry;
    }

    @Override
    public final UUID uniqueIdentifier() {
        return uniqueIdentifier;
    }

    @Override
    public final String humanIdentifier() {
        return humanIdentifier;
    }

    @Override
    public final String description() {
        return description;
    }

    @Override
    public final String author() {
        return author;
    }

    protected final EventBus<Event> eventBus() {
        return eventBus;
    }

    protected final FeatureRegistry<Feature> featureRegistry() {
        return featureRegistry;
    }
}
