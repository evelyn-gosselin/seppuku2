package pw.seppuku.plugin.exception;

public abstract class PluginException extends Exception {

  protected PluginException(final String message) {
    super(message);
  }
}
