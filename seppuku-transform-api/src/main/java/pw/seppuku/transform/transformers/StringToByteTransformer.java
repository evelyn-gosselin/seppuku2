package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;

public final class StringToByteTransformer extends AbstractTransformer<String, Byte> {

  public StringToByteTransformer() {
    super(String.class, Byte.class);
  }

  @Override
  public Byte transform(final String fromInstance) {
    return Byte.parseByte(fromInstance);
  }
}
