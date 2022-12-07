package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;

public final class StringToFloatTransformer extends AbstractTransformer<String, Float> {

  public StringToFloatTransformer() {
    super(String.class, Float.class);
  }

  @Override
  public Float transform(final String fromInstance) {
    return Float.parseFloat(fromInstance);
  }
}
