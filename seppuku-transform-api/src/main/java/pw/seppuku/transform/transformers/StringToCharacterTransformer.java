package pw.seppuku.transform.transformers;

import pw.seppuku.transform.AbstractTransformer;

public final class StringToCharacterTransformer extends AbstractTransformer<String, Character> {

  public StringToCharacterTransformer() {
    super(String.class, Character.class);
  }

  @Override
  public Character transform(final String fromInstance) {
    if (fromInstance.length() > 1) {
      return null; // TODO: Throw exception
    }

    return fromInstance.charAt(0);
  }
}
