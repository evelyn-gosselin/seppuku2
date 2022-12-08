package pw.seppuku.client.components.client.network

import net.minecraft.client.network.ClientPlayerInteractionManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

@JvmInline
value class ClientPlayerInteractionManagerUpdateBlockBreakingProgress(
    val onClientPlayerInteractionManagerUpdateBlockBreakingProgress: ClientPlayerInteractionManager.(BlockPos, Direction) -> Unit
)