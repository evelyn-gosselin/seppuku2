package pw.seppuku.vfly.toggleable.features;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.player.LocalPlayerSendPositionEvent;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.resolver.Inject;

public final class VanillaFlyFeature extends ToggleableFeature {

  private static final UUID SPRINT_UNIQUE_IDENTIFIER = UUID.fromString(
      "d1318327-57c1-4f3d-833e-9c36427e7318");
  private static final String SPRINT_HUMAN_IDENTIFIER = "vanilla-fly";
  private static final Version SPRINT_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SPRINT_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final EventBus eventBus;
  private final EventSubscriber<LocalPlayerSendPositionEvent> localPlayerSendPositionEventSubscriber = this::onLocalPlayerSendPosition;

  @Inject
  public VanillaFlyFeature(final EventBus eventBus) {
    super(SPRINT_UNIQUE_IDENTIFIER, SPRINT_HUMAN_IDENTIFIER, SPRINT_VERSION, SPRINT_AUTHORS);
    this.eventBus = eventBus;
  }

  private boolean onLocalPlayerSendPosition(final LocalPlayerSendPositionEvent event) {
    event.localPlayer().getAbilities().mayfly = true;
    return false;
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
