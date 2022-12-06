package pw.seppuku.events.minecraft.client.player;

import net.minecraft.client.player.LocalPlayer;

public final class LocalPlayerSendPositionEvent extends LocalPlayerEvent {

  public LocalPlayerSendPositionEvent(final LocalPlayer localPlayer) {
    super(localPlayer);
  }
}
