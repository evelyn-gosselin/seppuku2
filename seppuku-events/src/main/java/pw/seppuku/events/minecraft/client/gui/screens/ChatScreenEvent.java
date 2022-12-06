package pw.seppuku.events.minecraft.client.gui.screens;

import net.minecraft.client.gui.screens.ChatScreen;

public abstract class ChatScreenEvent {

  private final ChatScreen chatScreen;

  protected ChatScreenEvent(final ChatScreen chatScreen) {
    this.chatScreen = chatScreen;
  }

  public final ChatScreen chatScreen() {
    return chatScreen;
  }
}
