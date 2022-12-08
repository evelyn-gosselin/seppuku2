package pw.seppuku.atool

import net.minecraft.block.BlockState
import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.BlockPos
import pw.seppuku.client.components.client.network.ClientPlayerInteractionManagerAttackBlock
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import pw.seppuku.feature.PluginFeature

@PluginFeature
class AutoToolFeature(
    private val minecraftClient: MinecraftClient
) : AbstractFeature() {

    @Component
    private val humanIdentifier = HumanIdentifier("auto_tool")

    @Component
    private val toggle = Toggle()

    @Component
    private val clientPlayerInteractionManagerAttackBlock = ClientPlayerInteractionManagerAttackBlock { blockPos, _ ->
        val blockState = blockPos.getBlockState() ?: return@ClientPlayerInteractionManagerAttackBlock
        val bestRatedSlot = blockState.getBestRatedSlot() ?: return@ClientPlayerInteractionManagerAttackBlock
        bestRatedSlot.selectSlot()
    }

    private fun BlockPos.getBlockState(): BlockState? =
        this@AutoToolFeature.minecraftClient.world?.getBlockState(this)

    private fun BlockState.getBestRatedSlot(): Int? =
        (0..9).maxByOrNull {
            this.getSlotRating(it)
        }

    private fun BlockState.getSlotRating(index: Int): Float =
        this@AutoToolFeature.minecraftClient.player?.inventory?.getStack(index)?.getMiningSpeedMultiplier(this) ?: 0f

    private fun Int.selectSlot() {
        val player = this@AutoToolFeature.minecraftClient.player ?: return
        player.inventory.selectedSlot = this
    }
}