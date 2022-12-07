package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;

public final class StringToLongTransformer extends AbstractTransformer<String, Long> {

  public StringToLongTransformer() {
    super(String.class, Long.class);
  }

  @Override
  public Long transform(final String fromInstance) {
    return Long.parseLong(fromInstance);
  }
}
