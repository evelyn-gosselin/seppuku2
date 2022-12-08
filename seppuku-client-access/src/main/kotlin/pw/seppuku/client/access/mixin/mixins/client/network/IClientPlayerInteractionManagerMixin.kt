package pw.seppuku.client.access.mixin.mixins.client.network

import net.minecraft.client.network.ClientPlayerInteractionManager
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.gen.Accessor

@Mixin(ClientPlayerInteractionManager::class)
interface IClientPlayerInteractionManagerMixin {

    @Accessor("currentBreakingProgress")
    fun getCurrentBreakingProgress(): Float

    @Accessor("currentBreakingProgress")
    fun setCurrentBreakingProgress(currentBreakingProgress: Float)

    @Accessor("blockBreakingCooldown")
    fun getBlockBreakingCooldown(): Int

    @Accessor("blockBreakingCooldown")
    fun setBlockBreakingCooldown(blockBreakingCooldown: Int)
}