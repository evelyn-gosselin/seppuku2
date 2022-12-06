package pw.seppuku.client.mixin.mixins.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.events.minecraft.client.gui.GuiRenderEvent;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.mixin.Actual;
import pw.seppuku.plugin.exception.exceptions.DuplicateUniqueIdentifierPluginException;

@Mixin(Gui.class)
public abstract class GuiMixin implements Actual<Gui> {

    @Inject(method = "render", at = @At("TAIL"))
    private void onRenderTail(final PoseStack poseStack, final float f, final CallbackInfo callback) {
        Seppuku.instance().eventBus().publish(new GuiRenderEvent(actual(), poseStack));
    }
}
