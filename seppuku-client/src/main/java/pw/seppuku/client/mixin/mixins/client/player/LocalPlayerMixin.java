package pw.seppuku.client.mixin.mixins.client.player;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.events.minecraft.client.player.LocalPlayerSendPositionEvent;
import pw.seppuku.mixin.Actual;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin implements Actual<LocalPlayer> {

  @Inject(method = "sendPosition", at = @At("HEAD"), cancellable = true)
  private void onSendPositionHead(final CallbackInfo callback) {
    final var event = new LocalPlayerSendPositionEvent(actual());
      if (Seppuku.instance().eventBus().publish(event)) {
          callback.cancel();
      }
  }
}
