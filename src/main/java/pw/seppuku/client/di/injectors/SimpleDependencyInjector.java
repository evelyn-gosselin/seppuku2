package pw.seppuku.client.di.injectors;

import pw.seppuku.client.di.DependencyInjector;
import pw.seppuku.client.di.exception.DependencyInjectionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public final class SimpleDependencyInjector<T> implements DependencyInjector<T> {

    private final Map<Class<?>, Object> dependencyParameterTypeToInstanceMap;

    public SimpleDependencyInjector() {
        this(new HashMap<>());
    }

    public SimpleDependencyInjector(final Map<Class<?>, Object> dependencyParameterTypeToInstanceMap) {
        this.dependencyParameterTypeToInstanceMap = dependencyParameterTypeToInstanceMap;
    }

    @Override
    public <S extends T> S create(final Class<S> targetClass) throws DependencyInjectionException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final var bestConstructor = findBestConstructor(targetClass);
        final var constructorDependencies = resolveConstructorDependencies(bestConstructor);
        return bestConstructor.newInstance(constructorDependencies);
    }

    private <S extends T> Constructor<S> findBestConstructor(final Class<S> targetClass) throws DependencyInjectionException {
        final var compatibleConstructors = findCompatibleConstructors(targetClass);
        return compatibleConstructors.stream()
                .findFirst()
                .orElseThrow(DependencyInjectionException::new);
    }

    private <S extends T> List<Constructor<S>> findCompatibleConstructors(final Class<S> targetClass) {
        //noinspection unchecked
        final var compatibleConstructors = Arrays.stream(targetClass.getConstructors())
                .filter(constructor -> Arrays.stream(constructor.getParameterTypes()).allMatch(dependencyParameterTypeToInstanceMap::containsKey))
                .sorted(Comparator.comparingInt(constructor -> constructor.getParameterTypes().length))
                .map(constructor -> (Constructor<S>) constructor)
                .toList();

        Collections.reverse(compatibleConstructors);

        return compatibleConstructors;
    }

    private Object[] resolveConstructorDependencies(final Constructor<?> constructor) {
        return Arrays.stream(constructor.getParameterTypes())
                .map(dependencyParameterTypeToInstanceMap::get)
                .toArray();
    }
}
