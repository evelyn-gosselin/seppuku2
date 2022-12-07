package pw.seppuku.fm.toggleable.features;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.access.mixin.mixins.client.multiplayer.IMultiPlayerGameMode;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.multiplayer.MultiPlayerGameModeContinueDestroyBlockEvent;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.resolver.Inject;

public final class FastMineFeature extends ToggleableFeature {

  private final static UUID FAST_MINE_UNIQUE_IDENTIFIER = UUID.fromString(
      "6f1bc5f1-04b7-4db9-9216-d9ee32027a72");
  private final static String FAST_MINE_HUMAN_IDENTIFIER = "fast-mine";
  private final static Version FAST_MINE_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> FAST_MINE_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private static final EventSubscriber<MultiPlayerGameModeContinueDestroyBlockEvent> MULTI_PLAYER_GAME_MODE_CONTINUE_DESTROY_BLOCK_EVENT_SUBSCRIBER = event -> {
    final var multiPlayerGameMode = (IMultiPlayerGameMode) event.multiPlayerGameMode();
    final var destroyProgress = multiPlayerGameMode.getDestroyProgress();
    multiPlayerGameMode.setDestroyProgress(destroyProgress + 0.1f);
    return false;
  };

  private final EventBus eventBus;

  @Inject
  public FastMineFeature(final EventBus eventBus) {
    super(FAST_MINE_UNIQUE_IDENTIFIER, FAST_MINE_HUMAN_IDENTIFIER, FAST_MINE_VERSION,
        FAST_MINE_AUTHORS);
    this.eventBus = eventBus;
  }

  @Override
  public void load() {
    eventBus.subscribe(MultiPlayerGameModeContinueDestroyBlockEvent.class,
        MULTI_PLAYER_GAME_MODE_CONTINUE_DESTROY_BLOCK_EVENT_SUBSCRIBER);
  }

  @Override
  public void unload() {
    eventBus.unsubscribe(MultiPlayerGameModeContinueDestroyBlockEvent.class,
        MULTI_PLAYER_GAME_MODE_CONTINUE_DESTROY_BLOCK_EVENT_SUBSCRIBER);
  }
}
