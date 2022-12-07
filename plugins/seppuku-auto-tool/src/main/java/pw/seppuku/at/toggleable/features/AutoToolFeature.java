package pw.seppuku.at.toggleable.features;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.multiplayer.MultiPlayerGameModeStartDestroyBlockEvent;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.resolver.Inject;

public final class AutoToolFeature extends ToggleableFeature {

  private final static UUID AUTO_TOOL_UNIQUE_IDENTIFIER = UUID.fromString(
      "d6bd452c-146e-46a7-881f-4943c064006d");
  private final static String AUTO_TOOL_HUMAN_IDENTIFIER = "auto-tool";
  private final static Version AUTO_TOOL_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> AUTO_TOOL_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final EventBus eventBus;
  private final EventSubscriber<MultiPlayerGameModeStartDestroyBlockEvent> multiPlayerGameModeStartDestroyBlockEventSubscriber = this::onMultiPlayerGameModeStartDestroyBlock;

  @Inject
  public AutoToolFeature(final EventBus eventBus) {
    super(AUTO_TOOL_UNIQUE_IDENTIFIER, AUTO_TOOL_HUMAN_IDENTIFIER, AUTO_TOOL_VERSION,
        AUTO_TOOL_AUTHORS);
    this.eventBus = eventBus;
  }

  private boolean onMultiPlayerGameModeStartDestroyBlock(
      final MultiPlayerGameModeStartDestroyBlockEvent event) {
    final var minecraft = Minecraft.getInstance();

    final var localPlayer = minecraft.player;
    assert localPlayer != null;

    final var level = minecraft.level;
    assert level != null;

    final var blockState = level.getBlockState(event.blockPos());

    // TODO: Send packet
    getBestRatedSlotAgainstBlockState(localPlayer, blockState).ifPresent(
        slot -> localPlayer.getInventory().selected = slot);

    return false;
  }

  private Optional<Integer> getBestRatedSlotAgainstBlockState(final LocalPlayer localPlayer,
      final BlockState blockState) {
    final var inventory = localPlayer.getInventory();

    Integer bestSlot = null;
    Float bestSlotRating = null;
    for (var slot = 0; slot < 9; ++slot) {
      final var itemStack = inventory.getItem(slot);
      final var itemStackRating = calculateItemStackRatingAgainstBlockState(itemStack, blockState);
      if (itemStackRating > 1 && (bestSlotRating == null || itemStackRating > bestSlotRating)) {
        bestSlot = slot;
        bestSlotRating = itemStackRating;
      }
    }

    return Optional.ofNullable(bestSlot);
  }

  private float calculateItemStackRatingAgainstBlockState(final ItemStack itemStack,
      final BlockState blockState) {
    // TODO: take enchantments into account

    return itemStack.getDestroySpeed(blockState);
  }

  @Override
  public void load() {
    eventBus.subscribe(MultiPlayerGameModeStartDestroyBlockEvent.class,
        multiPlayerGameModeStartDestroyBlockEventSubscriber);
  }

  @Override
  public void unload() {
    eventBus.unsubscribe(MultiPlayerGameModeStartDestroyBlockEvent.class,
        multiPlayerGameModeStartDestroyBlockEventSubscriber);
  }
}
