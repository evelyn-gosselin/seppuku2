package pw.seppuku.client.mixin.mixins.client.player;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.client.event.events.client.player.LocalPlayerSendPositionEvent;
import pw.seppuku.client.mixin.Actual;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin implements Actual<LocalPlayer> {

    @Inject(method = "sendPosition", at = @At("HEAD"), cancellable = true)
    private void onSendPositionHead(final CallbackInfo callback) throws Exception {
        if (Seppuku.instance().eventBus().publish(new LocalPlayerSendPositionEvent(toActual()))) {
            callback.cancel();
        }
    }
}
