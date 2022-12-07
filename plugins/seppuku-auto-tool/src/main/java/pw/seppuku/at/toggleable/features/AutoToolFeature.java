package pw.seppuku.at.toggleable.features;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

  private static final EventSubscriber<MultiPlayerGameModeStartDestroyBlockEvent> MULTI_PLAYER_GAME_MODE_START_DESTROY_BLOCK_EVENT_SUBSCRIBER = event -> {
    final var minecraft = Minecraft.getInstance();

    final var localPlayer = minecraft.player;
    if (localPlayer == null) {
      return false;
    }

    final var level = minecraft.level;
    if (level == null) {
      return false;
    }

    final var blockState = level.getBlockState(event.blockPos());

    getBestRatedSlotAgainstBlockState(blockState, localPlayer).ifPresent(slot -> {
      localPlayer.getInventory().selected = slot;
    });

    return false;
  };

  private final EventBus eventBus;

  @Inject
  public AutoToolFeature(final EventBus eventBus) {
    super(AUTO_TOOL_UNIQUE_IDENTIFIER, AUTO_TOOL_HUMAN_IDENTIFIER, AUTO_TOOL_VERSION,
        AUTO_TOOL_AUTHORS);
    this.eventBus = eventBus;
  }

  private static Optional<Integer> getBestRatedSlotAgainstBlockState(final BlockState blockState,
      final LocalPlayer localPlayer) {
    return buildSlotToRatingMap(blockState, localPlayer).entrySet().stream()
        .max(AutoToolFeature::compareSlotToRatingEntries).map(Entry::getKey);
  }

  private static Map<Integer, Float> buildSlotToRatingMap(final BlockState blockState,
      final LocalPlayer localPlayer) {
    final var slotToRatingMap = new HashMap<Integer, Float>();

    for (var slot = 0; slot < 9; ++slot) {
      final var itemStack = localPlayer.getInventory().getItem(slot);
      final var itemStackRating = calculateItemStackRatingAgainstBlockState(itemStack, blockState);
      if (itemStackRating > 1) {
        slotToRatingMap.put(slot, itemStackRating);
      }
    }

    return slotToRatingMap;
  }

  private static float calculateItemStackRatingAgainstBlockState(final ItemStack itemStack,
      final BlockState blockState) {
    // TODO: take enchantments into account

    return itemStack.getDestroySpeed(blockState);
  }

  private static int compareSlotToRatingEntries(final Entry<Integer, Float> lhs,
      final Entry<Integer, Float> rhs) {
    return Float.compare(lhs.getValue(), rhs.getValue());
  }

  @Override
  public void load() {
    eventBus.subscribe(MultiPlayerGameModeStartDestroyBlockEvent.class,
        MULTI_PLAYER_GAME_MODE_START_DESTROY_BLOCK_EVENT_SUBSCRIBER);
  }

  @Override
  public void unload() {
    eventBus.unsubscribe(MultiPlayerGameModeStartDestroyBlockEvent.class,
        MULTI_PLAYER_GAME_MODE_START_DESTROY_BLOCK_EVENT_SUBSCRIBER);
  }
}
