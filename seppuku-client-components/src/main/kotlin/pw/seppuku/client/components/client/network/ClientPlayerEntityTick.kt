package pw.seppuku.client.components.client.network

import net.minecraft.client.network.ClientPlayerEntity

@JvmInline
value class ClientPlayerEntityTick(val onClientPlayerEntityTick: ClientPlayerEntity.() -> Unit)