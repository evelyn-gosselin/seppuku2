package pw.seppuku.sprint.toggleable.features;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.client.player.LocalPlayer;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.player.LocalPlayerSendPositionEvent;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.resolver.Inject;

public final class SprintFeature extends ToggleableFeature {

  private static final UUID SPRINT_UNIQUE_IDENTIFIER = UUID.fromString(
      "5746740b-a687-4018-8cd4-b8dc5a1b28ab");
  private static final String SPRINT_HUMAN_IDENTIFIER = "sprint";
  private static final Version SPRINT_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SPRINT_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final EventBus eventBus;
  private final EventSubscriber<LocalPlayerSendPositionEvent> localPlayerSendPositionEventSubscriber = this::onLocalPlayerSendPosition;

  @Inject
  public SprintFeature(final EventBus eventBus) {
    super(SPRINT_UNIQUE_IDENTIFIER, SPRINT_HUMAN_IDENTIFIER, SPRINT_VERSION, SPRINT_AUTHORS);
    this.eventBus = eventBus;
  }

  private boolean onLocalPlayerSendPosition(final LocalPlayerSendPositionEvent event) {
    event.localPlayer().setSprinting(shouldSprint(event.localPlayer()));
    return false;
  }

  private boolean shouldSprint(final LocalPlayer localPlayer) {
    return !localPlayer.input.shiftKeyDown && localPlayer.input.hasForwardImpulse();
  }

  @Override
  public void load() {
    eventBus.subscribe(LocalPlayerSendPositionEvent.class, localPlayerSendPositionEventSubscriber);
  }

  @Override
  public void unload() {
    eventBus.unsubscribe(LocalPlayerSendPositionEvent.class,
        localPlayerSendPositionEventSubscriber);
  }
}
