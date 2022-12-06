package pw.seppuku.client.mixin;

public interface Actual<T> {

    default T toActual() {
        //noinspection unchecked
        return (T) this;
    }
}
