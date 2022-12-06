package pw.seppuku.events.minecraft.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Gui;

public final class GuiRenderEvent extends GuiEvent {

    private final PoseStack poseStack;

    public GuiRenderEvent(final Gui gui, final PoseStack poseStack) {
        super(gui);
        this.poseStack = poseStack;
    }

    public PoseStack poseStack() {
        return poseStack;
    }
}
