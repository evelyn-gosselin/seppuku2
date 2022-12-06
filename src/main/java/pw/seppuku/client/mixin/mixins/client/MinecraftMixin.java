package pw.seppuku.client.mixin.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.client.event.events.client.MinecraftConstructorEvent;
import pw.seppuku.client.mixin.Actual;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements Actual<Minecraft> {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructorTail(final GameConfig gameConfig, final CallbackInfo callback) throws Exception {
        Seppuku.instance().eventBus().publish(new MinecraftConstructorEvent(toActual()));
    }
}
