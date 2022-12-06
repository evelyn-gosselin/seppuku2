package pw.seppuku.feature.exception.exceptions;

import java.util.UUID;
import pw.seppuku.feature.exception.FeatureException;

public final class DuplicateUniqueIdentifierFeatureException extends FeatureException {

  public DuplicateUniqueIdentifierFeatureException(final UUID uniqueIdentifier) {
    super("Duplicate unique identifier " + uniqueIdentifier);
  }
}
