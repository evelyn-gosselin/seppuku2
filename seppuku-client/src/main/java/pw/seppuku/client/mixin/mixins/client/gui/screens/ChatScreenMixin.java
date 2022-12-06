package pw.seppuku.client.mixin.mixins.client.gui.screens;

import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.seppuku.client.Seppuku;
import pw.seppuku.events.minecraft.client.gui.screens.ChatScreenHandleInputEvent;
import pw.seppuku.mixin.Actual;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin implements Actual<ChatScreen> {

    @Inject(method = "handleChatInput", at = @At("HEAD"), cancellable = true)
    private void onHandleChatInput(final String string, final boolean bl, final CallbackInfoReturnable<Boolean> callback) {
        final var event = new ChatScreenHandleInputEvent(actual(), string);
        if (Seppuku.instance().eventBus().publish(event)) {
            callback.setReturnValue(true);
        }
    }
}
