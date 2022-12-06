package pw.seppuku.events.minecraft.client.gui.screens;

import net.minecraft.client.gui.screens.ChatScreen;

public final class ChatScreenHandleInputEvent extends ChatScreenEvent {

  private final String message;

  public ChatScreenHandleInputEvent(final ChatScreen chatScreen, final String message) {
    super(chatScreen);
    this.message = message;
  }

  public String message() {
    return message;
  }
}
