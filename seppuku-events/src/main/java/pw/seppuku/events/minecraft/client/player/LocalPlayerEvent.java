package pw.seppuku.events.minecraft.client.player;

import net.minecraft.client.player.LocalPlayer;

public abstract class LocalPlayerEvent {

  private final LocalPlayer localPlayer;

  protected LocalPlayerEvent(final LocalPlayer localPlayer) {
    this.localPlayer = localPlayer;
  }

  public final LocalPlayer localPlayer() {
    return localPlayer;
  }
}
