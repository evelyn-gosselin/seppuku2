package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;

public final class StringToShortTransformer extends AbstractTransformer<String, Short> {

  public StringToShortTransformer() {
    super(String.class, Short.class);
  }

  @Override
  public Short transform(final String fromInstance) {
    return Short.parseShort(fromInstance);
  }
}
