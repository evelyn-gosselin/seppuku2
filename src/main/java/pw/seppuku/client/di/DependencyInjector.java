package pw.seppuku.client.di;

public interface DependencyInjector<T> {

    <S extends T> S create(final Class<S> targetClass) throws Exception;
}
