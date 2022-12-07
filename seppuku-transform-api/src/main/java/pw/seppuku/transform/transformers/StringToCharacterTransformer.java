package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public final class StringToCharacterTransformer extends AbstractTransformer<String, Character> {

  public StringToCharacterTransformer() {
    super(String.class, Character.class);
  }

  @Override
  public Character transform(final String fromInstance) throws TransformLazyException {
    if (fromInstance.length() != 1) {
      throw new TransformLazyException("Invalid character '" + fromInstance + "'");
    }

    return fromInstance.charAt(0);
  }
}
