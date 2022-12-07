package pw.seppuku.transform.transformers;

import java.util.Map;
import java.util.Optional;
import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToBooleanTransformer extends AbstractTransformer<String, Boolean> {

  // @formatter:off
  private final static Map<String, Boolean> STRING_TO_BOOLEAN_MAP = Map.of(
      "true", true,
      "on", true,
      "false", false,
      "off", false
  );
  // @formatter:on

  public StringToBooleanTransformer() {
    super(String.class, Boolean.class);
  }

  @Override
  public Boolean transform(final String fromInstance) throws TransformLazyException {
    return Optional.of(STRING_TO_BOOLEAN_MAP.get(fromInstance))
        .orElseThrow(() -> new TransformLazyException("Unknown boolean '" + fromInstance + "'"));
  }
}
