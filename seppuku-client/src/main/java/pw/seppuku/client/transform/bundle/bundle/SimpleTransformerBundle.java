package pw.seppuku.client.transform.bundle.bundle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pw.seppuku.transform.Transformer;
import pw.seppuku.transform.bundle.TransformerBundle;
import pw.seppuku.transform.exception.TransformException;

public final class SimpleTransformerBundle implements TransformerBundle {

  private final Map<Class<?>, Map<Class<?>, Transformer<?, ?>>> fromTypeToTransformedTypeToTransformerMap;

  public SimpleTransformerBundle() {
    this(new HashMap<>());
  }

  public SimpleTransformerBundle(
      final Map<Class<?>, Map<Class<?>, Transformer<?, ?>>> fromTypeToTransformedTypeToTransformerMap) {
    this.fromTypeToTransformedTypeToTransformerMap = fromTypeToTransformedTypeToTransformerMap;
  }

  @Override
  public void add(final Transformer<?, ?> transformerToAdd) {
    fromTypeToTransformedTypeToTransformerMap.putIfAbsent(transformerToAdd.fromType(),
        new HashMap<>());

    fromTypeToTransformedTypeToTransformerMap.get(transformerToAdd.fromType())
        .put(transformerToAdd.transformedType(), transformerToAdd);
  }

  @Override
  public void addAll(final List<Transformer<?, ?>> transformersToAdd) {
    transformersToAdd.forEach(this::add);
  }

  @Override
  public <F, T> T transform(Class<F> fromType, Class<T> transformedType, F fromInstance)
      throws TransformException {
    //noinspection unchecked
    final var transformer = (Transformer<F, T>) fromTypeToTransformedTypeToTransformerMap.get(
        fromType).get(transformedType);

    return transformer.transform(fromInstance);
  }
}
