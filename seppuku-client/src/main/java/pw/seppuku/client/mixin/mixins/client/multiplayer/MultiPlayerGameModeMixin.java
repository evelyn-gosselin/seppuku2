package pw.seppuku.client.mixin.mixins.client.multiplayer;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.seppuku.client.Seppuku;
import pw.seppuku.events.minecraft.client.multiplayer.MultiPlayerGameModeDestroyBlockEvent;
import pw.seppuku.events.minecraft.client.multiplayer.MultiPlayerGameModeStartDestroyBlockEvent;
import pw.seppuku.mixin.Actual;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin implements Actual<MultiPlayerGameMode> {

  @Inject(method = "startDestroyBlock", at = @At("HEAD"), cancellable = true)
  private void onStartDestroyBlockHead(final BlockPos blockPos, final Direction direction,
      final CallbackInfoReturnable<Boolean> callback) {
    if (Seppuku.instance().eventBus()
        .publish(new MultiPlayerGameModeStartDestroyBlockEvent(actual(), blockPos, direction))) {
      callback.setReturnValue(false);
    }
  }

  @Inject(method = "destroyBlock", at = @At("HEAD"), cancellable = true)
  private void onDestroyBlockHead(final BlockPos blockPos,
      final CallbackInfoReturnable<Boolean> callback) {
    if (Seppuku.instance().eventBus()
        .publish(new MultiPlayerGameModeDestroyBlockEvent(actual(), blockPos))) {
      callback.setReturnValue(false);
    }
  }
}
