package pw.seppuku.access.mixin.mixins.client.multiplayer;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MultiPlayerGameMode.class)
public interface IMultiPlayerGameMode {

  @Accessor("destroyProgress")
  float getDestroyProgress();

  @Accessor("destroyProgress")
  void setDestroyProgress(final float destroyProgress);
}
