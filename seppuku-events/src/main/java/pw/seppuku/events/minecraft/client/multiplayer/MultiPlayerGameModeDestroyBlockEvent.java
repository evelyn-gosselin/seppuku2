package pw.seppuku.events.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;

public final class MultiPlayerGameModeDestroyBlockEvent extends MultiPlayerGameModeEvent {

  private final BlockPos blockPos;

  public MultiPlayerGameModeDestroyBlockEvent(final MultiPlayerGameMode multiPlayerGameMode,
      final BlockPos blockPos) {
    super(multiPlayerGameMode);
    this.blockPos = blockPos;
  }

  public BlockPos blockPos() {
    return blockPos;
  }
}
