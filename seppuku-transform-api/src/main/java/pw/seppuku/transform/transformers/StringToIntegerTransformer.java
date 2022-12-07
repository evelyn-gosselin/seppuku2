package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToIntegerTransformer extends AbstractTransformer<String, Integer> {

  public StringToIntegerTransformer() {
    super(String.class, Integer.class);
  }

  @Override
  public Integer transform(final String fromInstance) throws TransformLazyException {
    try {
      return Integer.parseInt(fromInstance);
    } catch (final NumberFormatException exception) {
      throw new TransformLazyException("Invalid integer '" + fromInstance + "'");
    }
  }
}
