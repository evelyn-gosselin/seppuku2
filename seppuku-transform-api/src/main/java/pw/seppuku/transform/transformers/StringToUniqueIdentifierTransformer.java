package pw.seppuku.transform.transformers;

import java.util.UUID;
import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.TransformException;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToUniqueIdentifierTransformer extends AbstractTransformer<String, UUID> {

  public StringToUniqueIdentifierTransformer() {
    super(String.class, UUID.class);
  }

  @Override
  public UUID transform(final String fromInstance) throws TransformException {
    try {
      return UUID.fromString(fromInstance);
    } catch (final IllegalArgumentException exception) {
      throw new TransformLazyException("Invalid unique identifier '" + fromInstance + "'");
    }
  }
}
