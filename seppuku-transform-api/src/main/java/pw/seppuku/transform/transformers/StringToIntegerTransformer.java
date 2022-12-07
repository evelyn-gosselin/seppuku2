package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;

public final class StringToIntegerTransformer extends AbstractTransformer<String, Integer> {

  public StringToIntegerTransformer() {
    super(String.class, Integer.class);
  }

  @Override
  public Integer transform(final String fromInstance) {
    return Integer.parseInt(fromInstance);
  }
}
