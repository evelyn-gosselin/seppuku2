package pw.seppuku.client.components.client

import net.minecraft.client.MinecraftClient

@JvmInline
value class MinecraftClientInit(val onMinecraftClientInit: MinecraftClient.() -> Unit)