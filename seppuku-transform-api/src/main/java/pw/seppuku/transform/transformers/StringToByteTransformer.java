package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToByteTransformer extends AbstractTransformer<String, Byte> {

  public StringToByteTransformer() {
    super(String.class, Byte.class);
  }

  @Override
  public Byte transform(final String fromInstance) throws TransformLazyException {
    try {
      return Byte.parseByte(fromInstance);
    } catch (final NumberFormatException exception) {
      throw new TransformLazyException("Invalid byte '" + fromInstance + "'");
    }
  }
}
