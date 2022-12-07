package pw.seppuku.events.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public final class MultiPlayerGameModeStartDestroyBlockEvent extends MultiPlayerGameModeEvent {

  private final BlockPos blockPos;
  private final Direction direction;

  public MultiPlayerGameModeStartDestroyBlockEvent(final MultiPlayerGameMode multiPlayerGameMode,
      final BlockPos blockPos, final Direction direction) {
    super(multiPlayerGameMode);
    this.blockPos = blockPos;
    this.direction = direction;
  }

  public BlockPos blockPos() {
    return blockPos;
  }

  public Direction direction() {
    return direction;
  }
}
