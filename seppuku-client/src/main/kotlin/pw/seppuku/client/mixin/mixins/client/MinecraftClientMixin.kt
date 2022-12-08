package pw.seppuku.client.mixin.mixins.client

import net.minecraft.client.MinecraftClient
import net.minecraft.client.RunArgs
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import pw.seppuku.client.Seppuku
import pw.seppuku.client.components.client.MinecraftClientInit
import pw.seppuku.client.feature.running
import pw.seppuku.client.feature.withComponent
import pw.seppuku.client.mixin.ActualThis

@Mixin(MinecraftClient::class)
abstract class MinecraftClientMixin : ActualThis<MinecraftClient> {

    @Inject(method = ["<init>"], at = [At("TAIL")])
    private fun onInit(runArgs: RunArgs, callback: CallbackInfo) =
        Seppuku.featureRepository.findAll()
            .running()
            .withComponent<MinecraftClientInit>()
            .forEach { it.onMinecraftClientInit(actualThis, runArgs) }
}
