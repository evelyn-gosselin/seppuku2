package pw.seppuku.mixin;

public interface Actual<T> {

  default T actual() {
    //noinspection unchecked
    return (T) this;
  }
}
