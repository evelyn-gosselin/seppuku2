package pw.seppuku.client.event.events.client.player;

import net.minecraft.client.player.LocalPlayer;
import pw.seppuku.client.event.Event;

public record LocalPlayerSendPositionEvent(LocalPlayer localPlayer) implements Event {
}
