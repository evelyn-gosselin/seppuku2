package pw.seppuku.plugin.exception.exceptions;

import java.util.UUID;
import pw.seppuku.plugin.exception.PluginException;

public final class DuplicateUniqueIdentifierPluginException extends PluginException {

  public DuplicateUniqueIdentifierPluginException(final UUID uniqueIdentifier) {
    super("Duplicate unique identifier " + uniqueIdentifier);
  }
}
