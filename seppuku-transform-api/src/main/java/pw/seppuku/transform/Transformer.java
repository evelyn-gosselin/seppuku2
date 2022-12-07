package pw.seppuku.transform;

import java.util.List;
import pw.seppuku.transform.exception.TransformException;
import pw.seppuku.transform.transformers.StringToBooleanTransformer;
import pw.seppuku.transform.transformers.StringToByteTransformer;
import pw.seppuku.transform.transformers.StringToCharacterTransformer;
import pw.seppuku.transform.transformers.StringToDoubleTransformer;
import pw.seppuku.transform.transformers.StringToFloatTransformer;
import pw.seppuku.transform.transformers.StringToIntegerTransformer;
import pw.seppuku.transform.transformers.StringToLongTransformer;
import pw.seppuku.transform.transformers.StringToShortTransformer;

public interface Transformer<F, T> {

  static List<Transformer<?, ?>> builtinTransformers() {
    // @formatter:off
    return List.of(
        new StringToBooleanTransformer(),
        new StringToByteTransformer(),
        new StringToCharacterTransformer(),
        new StringToDoubleTransformer(),
        new StringToFloatTransformer(),
        new StringToIntegerTransformer(),
        new StringToLongTransformer(),
        new StringToShortTransformer()
    );
    // @formatter:on
  }

  T transform(final F fromInstance) throws TransformException;

  Class<F> fromType();

  Class<T> transformedType();
}
