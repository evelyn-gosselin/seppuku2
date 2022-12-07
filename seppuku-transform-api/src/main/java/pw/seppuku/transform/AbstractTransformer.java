package pw.seppuku.transform;

public abstract class AbstractTransformer<F, T> implements Transformer<F, T> {

  private final Class<F> fromType;
  private final Class<T> transformedType;

  protected AbstractTransformer(final Class<F> fromType, final Class<T> transformedType) {
    this.fromType = fromType;
    this.transformedType = transformedType;
  }

  @Override
  public final Class<F> fromType() {
    return fromType;
  }

  @Override
  public final Class<T> transformedType() {
    return transformedType;
  }
}
