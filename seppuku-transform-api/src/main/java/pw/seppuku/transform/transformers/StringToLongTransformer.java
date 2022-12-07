package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToLongTransformer extends AbstractTransformer<String, Long> {

  public StringToLongTransformer() {
    super(String.class, Long.class);
  }

  @Override
  public Long transform(final String fromInstance) throws TransformLazyException {
    try {
      return Long.parseLong(fromInstance);
    } catch (final NumberFormatException exception) {
      throw new TransformLazyException("Invalid long '" + fromInstance + "'");
    }
  }
}
