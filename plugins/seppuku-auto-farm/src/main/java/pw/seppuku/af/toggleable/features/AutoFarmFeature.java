package pw.seppuku.af.toggleable.features;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.player.LocalPlayerSendPositionEvent;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.resolver.Inject;

public final class AutoFarmFeature extends ToggleableFeature {

  private final static UUID AUTO_FARM_UNIQUE_IDENTIFIER = UUID.fromString(
      "4e759150-4a48-452a-9c56-a286802745a1");
  private final static String AUTO_FARM_HUMAN_IDENTIFIER = "auto-farm";
  private final static Version AUTO_FARM_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> AUTO_FARM_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private static final float AUTO_FARM_RADIUS = 3;

  private final EventBus eventBus;
  private final EventSubscriber<LocalPlayerSendPositionEvent> localPlayerSendPositionEventSubscriber = this::onLocalPlayerSendPosition;

  @Inject
  public AutoFarmFeature(final EventBus eventBus) {
    super(AUTO_FARM_UNIQUE_IDENTIFIER, AUTO_FARM_HUMAN_IDENTIFIER, AUTO_FARM_VERSION,
        AUTO_FARM_AUTHORS);
    this.eventBus = eventBus;
  }

  private boolean onLocalPlayerSendPosition(final LocalPlayerSendPositionEvent event) {
    final var minecraft = Minecraft.getInstance();

    final var multiPlayerGameMode = minecraft.gameMode;
    assert multiPlayerGameMode != null;

    final var localPlayer = event.localPlayer();

    final var level = localPlayer.level;

    final var blockPosIterable = getBlockPosIterableWithinRadius(localPlayer.blockPosition());
    // TODO: How the hell do I sort this thing...

    for (final var blockPos : blockPosIterable) {
      final var blockState = level.getBlockState(blockPos);
      final var block = blockState.getBlock();
      if (!isBlockPosFarmBlock(block)) {
        continue;
      }

      final var plantBlockPos = blockPos.above();
      final var plantBlockState = level.getBlockState(plantBlockPos);
      final var plantBlock = plantBlockState.getBlock();
      if (!isBlockPosAirBlock(plantBlock)) {
        continue;
      }

      final var blockPosVec = new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
      final var blockHitResult = new BlockHitResult(blockPosVec, Direction.UP, blockPos, false);
      multiPlayerGameMode.useItemOn(localPlayer, InteractionHand.MAIN_HAND, blockHitResult);
      break;
    }

    return false;
  }

  private Iterable<BlockPos> getBlockPosIterableWithinRadius(final BlockPos center) {
    final var from = center.offset(-AUTO_FARM_RADIUS, -AUTO_FARM_RADIUS, -AUTO_FARM_RADIUS);
    final var to = center.offset(AUTO_FARM_RADIUS, AUTO_FARM_RADIUS, AUTO_FARM_RADIUS);

    return BlockPos.betweenClosed(from, to);
  }

  private boolean isBlockPosFarmBlock(final Block block) {
    return block instanceof FarmBlock;
  }

  private boolean isBlockPosAirBlock(final Block block) {
    return block instanceof AirBlock;
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
