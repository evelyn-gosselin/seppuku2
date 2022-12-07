package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToFloatTransformer extends AbstractTransformer<String, Float> {

  public StringToFloatTransformer() {
    super(String.class, Float.class);
  }

  @Override
  public Float transform(final String fromInstance) throws TransformLazyException {
    try {
      return Float.parseFloat(fromInstance);
    } catch (final NumberFormatException exception) {
      throw new TransformLazyException("Invalid float '" + fromInstance + "'");
    }
  }
}
