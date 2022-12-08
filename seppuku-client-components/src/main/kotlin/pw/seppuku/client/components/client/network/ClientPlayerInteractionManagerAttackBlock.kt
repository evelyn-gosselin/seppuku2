package pw.seppuku.client.components.client.network

import net.minecraft.client.network.ClientPlayerInteractionManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

@JvmInline
value class ClientPlayerInteractionManagerAttackBlock(
    val onClientPlayerInteractionManagerAttackBlock: ClientPlayerInteractionManager.(BlockPos, Direction) -> Unit
)