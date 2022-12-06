package pw.seppuku.client.feature.toggleable.features;

import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.bus.EventBus;
import pw.seppuku.client.event.events.client.player.LocalPlayerSendPositionEvent;
import pw.seppuku.client.event.listener.EventListener;
import pw.seppuku.client.feature.Feature;
import pw.seppuku.client.feature.registry.FeatureRegistry;
import pw.seppuku.client.feature.toggleable.ToggleableFeature;

import java.util.UUID;

public final class SprintFeature extends ToggleableFeature {

    private static final UUID SPRINT_UUID = UUID.fromString("2de70ae5-584c-4874-a802-6f9b1c5aad76");

    private static final EventListener<LocalPlayerSendPositionEvent> LOCAL_PLAYER_SEND_POSITION_EVENT_EVENT_LISTENER = event -> {
        event.localPlayer().setSprinting(true);
        return false;
    };

    public SprintFeature(final EventBus<Event> eventBus, final FeatureRegistry<Feature> featureRegistry) {
        super(SPRINT_UUID, "sprint", "Placeholder description.", "Ossian Winter <ossian@hey.com>", eventBus, featureRegistry);
    }

    @Override
    protected void onActivation() {
        eventBus().add(LocalPlayerSendPositionEvent.class, LOCAL_PLAYER_SEND_POSITION_EVENT_EVENT_LISTENER);
    }

    @Override
    protected void onDeactivation() {
        eventBus().remove(LocalPlayerSendPositionEvent.class, LOCAL_PLAYER_SEND_POSITION_EVENT_EVENT_LISTENER);
    }
}
