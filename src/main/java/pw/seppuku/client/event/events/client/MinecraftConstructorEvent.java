package pw.seppuku.client.event.events.client;

import net.minecraft.client.Minecraft;
import pw.seppuku.client.event.Event;

public record MinecraftConstructorEvent(Minecraft minecraft) implements Event {}
