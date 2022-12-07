package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;

public final class StringToDoubleTransformer extends AbstractTransformer<String, Double> {

  public StringToDoubleTransformer() {
    super(String.class, Double.class);
  }

  @Override
  public Double transform(final String fromInstance) {
    return Double.parseDouble(fromInstance);
  }
}
