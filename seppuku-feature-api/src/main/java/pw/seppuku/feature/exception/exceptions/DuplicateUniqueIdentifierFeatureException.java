package pw.seppuku.feature.exception.exceptions;

import pw.seppuku.feature.exception.FeatureException;

import java.util.UUID;

public final class DuplicateUniqueIdentifierFeatureException extends FeatureException {

    public DuplicateUniqueIdentifierFeatureException(final UUID uniqueIdentifier) {
        super("Duplicate unique identifier " + uniqueIdentifier);
    }
}
