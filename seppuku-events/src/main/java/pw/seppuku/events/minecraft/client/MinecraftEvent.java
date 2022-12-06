package pw.seppuku.events.minecraft.client;

import net.minecraft.client.Minecraft;

public abstract class MinecraftEvent {

    private final Minecraft minecraft;

    protected MinecraftEvent(final Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public final Minecraft minecraft() {
        return minecraft;
    }
}
