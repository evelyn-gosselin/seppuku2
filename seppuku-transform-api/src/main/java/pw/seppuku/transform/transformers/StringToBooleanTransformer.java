package pw.seppuku.transform.transformers;

import java.util.Map;
import pw.seppuku.transform.AbstractTransformer;

public final class StringToBooleanTransformer extends AbstractTransformer<String, Boolean> {

  private final static Map<String, Boolean> STRING_TO_BOOLEAN_MAP = Map.of("true", true, "on", true,
      "false", false, "off", false);

  public StringToBooleanTransformer() {
    super(String.class, Boolean.class);
  }

  @Override
  public Boolean transform(final String fromInstance) {
    return STRING_TO_BOOLEAN_MAP.get(fromInstance);
  }
}
