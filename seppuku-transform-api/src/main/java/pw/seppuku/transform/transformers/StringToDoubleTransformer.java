package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToDoubleTransformer extends AbstractTransformer<String, Double> {

  public StringToDoubleTransformer() {
    super(String.class, Double.class);
  }

  @Override
  public Double transform(final String fromInstance) throws TransformLazyException {
    try {
      return Double.parseDouble(fromInstance);
    } catch (final NumberFormatException exception) {
      throw new TransformLazyException("Invalid double '" + fromInstance + "'");
    }
  }
}
