package pw.seppuku.events.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;

public abstract class MultiPlayerGameModeEvent {

  private final MultiPlayerGameMode multiPlayerGameMode;

  protected MultiPlayerGameModeEvent(final MultiPlayerGameMode multiPlayerGameMode) {
    this.multiPlayerGameMode = multiPlayerGameMode;
  }

  public MultiPlayerGameMode multiPlayerGameMode() {
    return multiPlayerGameMode;
  }
}
