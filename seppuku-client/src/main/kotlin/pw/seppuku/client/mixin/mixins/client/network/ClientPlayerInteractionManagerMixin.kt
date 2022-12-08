package pw.seppuku.client.mixin.mixins.client.network

import net.minecraft.client.network.ClientPlayerInteractionManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import pw.seppuku.client.Seppuku
import pw.seppuku.client.components.client.network.ClientPlayerInteractionManagerAttackBlock
import pw.seppuku.client.components.client.network.ClientPlayerInteractionManagerUpdateBlockBreakingProgress
import pw.seppuku.client.mixin.ActualThis
import pw.seppuku.components.Toggle
import pw.seppuku.feature.filterComponent
import pw.seppuku.feature.mapComponent

@Mixin(ClientPlayerInteractionManager::class)
abstract class ClientPlayerInteractionManagerMixin : ActualThis<ClientPlayerInteractionManager> {

    @Inject(method = ["attackBlock"], at = [At("HEAD")])
    private fun onAttackBlockHead(
        blockPos: BlockPos,
        direction: Direction,
        callback: CallbackInfoReturnable<Boolean>
    ) =
        Seppuku.featureRepository.findAll()
            .filterComponent<Toggle>(false)
            .mapComponent<ClientPlayerInteractionManagerAttackBlock>()
            .forEach { it.onClientPlayerInteractionManagerAttackBlock(actualThis, blockPos, direction) }

    @Inject(method = ["updateBlockBreakingProgress"], at = [At("HEAD")])
    private fun onUpdateBlockBreakingProgress(
        blockPos: BlockPos,
        direction: Direction,
        callback: CallbackInfoReturnable<Boolean>
    ) =
        Seppuku.featureRepository.findAll()
            .filterComponent<Toggle>(false)
            .mapComponent<ClientPlayerInteractionManagerUpdateBlockBreakingProgress>()
            .forEach { it.onClientPlayerInteractionManagerUpdateBlockBreakingProgress(actualThis, blockPos, direction) }
}