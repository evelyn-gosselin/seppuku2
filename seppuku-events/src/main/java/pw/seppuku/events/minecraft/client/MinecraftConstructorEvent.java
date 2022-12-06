package pw.seppuku.events.minecraft.client;

import net.minecraft.client.Minecraft;

public final class MinecraftConstructorEvent extends MinecraftEvent {

    public MinecraftConstructorEvent(final Minecraft minecraft) {
        super(minecraft);
    }
}
