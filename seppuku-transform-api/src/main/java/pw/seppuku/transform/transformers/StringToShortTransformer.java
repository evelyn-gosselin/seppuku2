package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToShortTransformer extends AbstractTransformer<String, Short> {

  public StringToShortTransformer() {
    super(String.class, Short.class);
  }

  @Override
  public Short transform(final String fromInstance) throws TransformLazyException {
    try {
      return Short.parseShort(fromInstance);
    } catch (final NumberFormatException exception) {
      throw new TransformLazyException("Invalid short '" + fromInstance + "'");
    }
  }
}
