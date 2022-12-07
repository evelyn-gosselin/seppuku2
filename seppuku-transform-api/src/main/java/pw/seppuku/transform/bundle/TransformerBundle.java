package pw.seppuku.transform.bundle;

import java.util.List;
import pw.seppuku.transform.Transformer;
import pw.seppuku.transform.exception.TransformException;

public interface TransformerBundle {

  void add(final Transformer<?, ?> transformerToAdd);

  void addAll(final List<Transformer<?, ?>> transformersToAdd);

  <F, T> T transform(final Class<F> fromType, final Class<T> transformedType, final F fromInstance)
      throws TransformException;
}
