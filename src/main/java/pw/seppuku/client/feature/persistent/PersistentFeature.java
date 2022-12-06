package pw.seppuku.client.feature.persistent;

import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.bus.EventBus;
import pw.seppuku.client.feature.AbstractFeature;
import pw.seppuku.client.feature.Feature;
import pw.seppuku.client.feature.exception.FeatureException;
import pw.seppuku.client.feature.registry.FeatureRegistry;

import java.util.UUID;

public abstract class PersistentFeature extends AbstractFeature {

    protected PersistentFeature(final UUID uniqueIdentifier, final String humanIdentifier, final String description, final String author, final EventBus<Event> eventBus, final FeatureRegistry<Feature> featureRegistry) {
        super(uniqueIdentifier, humanIdentifier, description, author, eventBus, featureRegistry);
    }

    @Override
    public final boolean running() {
        return true;
    }

    @Override
    public final void setRunning(final boolean running) throws FeatureException {
        throw new FeatureException();
    }
}
