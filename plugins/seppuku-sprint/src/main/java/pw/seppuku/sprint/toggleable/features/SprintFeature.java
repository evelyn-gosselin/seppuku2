package pw.seppuku.sprint.toggleable.features;

import net.minecraft.client.player.LocalPlayer;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.player.LocalPlayerSendPositionEvent;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class SprintFeature extends ToggleableFeature {

    private static final UUID SPRINT_UNIQUE_IDENTIFIER = UUID.fromString("5746740b-a687-4018-8cd4-b8dc5a1b28ab");
    private static final String SPRINT_HUMAN_IDENTIFIER = "sprint";
    private static final Version SPRINT_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> SPRINT_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    private static final EventSubscriber<LocalPlayerSendPositionEvent> LOCAL_PLAYER_SEND_POSITION_EVENT_EVENT_SUBSCRIBER = event -> {
        event.localPlayer().setSprinting(shouldSprint(event.localPlayer()));
        return false;
    };

    private final EventBus eventBus;

    public SprintFeature(final EventBus eventBus) {
        super(SPRINT_UNIQUE_IDENTIFIER, SPRINT_HUMAN_IDENTIFIER, SPRINT_VERSION, SPRINT_AUTHORS);
        this.eventBus = eventBus;
    }

    @Override
    public void load() {
        eventBus.subscribe(LocalPlayerSendPositionEvent.class, LOCAL_PLAYER_SEND_POSITION_EVENT_EVENT_SUBSCRIBER);
    }

    @Override
    public void unload() {
        eventBus.unsubscribe(LocalPlayerSendPositionEvent.class, LOCAL_PLAYER_SEND_POSITION_EVENT_EVENT_SUBSCRIBER);
    }

    private static boolean shouldSprint(final LocalPlayer localPlayer) {
        return localPlayer.isOnGround() && !localPlayer.input.shiftKeyDown && localPlayer.input.hasForwardImpulse();
    }
}
