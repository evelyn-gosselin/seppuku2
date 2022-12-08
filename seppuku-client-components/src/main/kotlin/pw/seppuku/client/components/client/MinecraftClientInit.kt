package pw.seppuku.client.components.client

import net.minecraft.client.MinecraftClient
import net.minecraft.client.RunArgs

@JvmInline
value class MinecraftClientInit(val onMinecraftClientInit: MinecraftClient.(runArgs: RunArgs) -> Unit)