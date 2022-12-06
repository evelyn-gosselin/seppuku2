package pw.seppuku.plugin.exception.exceptions;

import pw.seppuku.plugin.exception.PluginException;

import java.util.UUID;

public final class DuplicateUniqueIdentifierPluginException extends PluginException {

    public DuplicateUniqueIdentifierPluginException(final UUID uniqueIdentifier) {
        super("Duplicate unique identifier " + uniqueIdentifier);
    }
}
