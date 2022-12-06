package pw.seppuku.events.minecraft.client.gui;

import net.minecraft.client.gui.Gui;

public abstract class GuiEvent {

  private final Gui gui;

  protected GuiEvent(final Gui gui) {
    this.gui = gui;
  }

  public final Gui gui() {
    return gui;
  }
}
