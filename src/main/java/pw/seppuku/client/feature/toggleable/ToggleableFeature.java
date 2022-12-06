package pw.seppuku.client.feature.toggleable;

import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.bus.EventBus;
import pw.seppuku.client.feature.AbstractFeature;
import pw.seppuku.client.feature.Feature;
import pw.seppuku.client.feature.registry.FeatureRegistry;

import java.util.UUID;

public abstract class ToggleableFeature extends AbstractFeature {

    private boolean running;

    protected ToggleableFeature(final UUID uniqueIdentifier, final String humanIdentifier, final String description, final String author, final EventBus<Event> eventBus, final FeatureRegistry<Feature> featureRegistry) {
        super(uniqueIdentifier, humanIdentifier, description, author, eventBus, featureRegistry);
    }

    @Override
    public final boolean running() {
        return running;
    }

    @Override
    public final void setRunning(final boolean running) {
        this.running = running;

        if (running) {
            onActivation();
        } else {
            onDeactivation();
        }
    }

    protected abstract void onActivation();

    protected abstract void onDeactivation();
}
