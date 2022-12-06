package pw.seppuku.resolver.resolvers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pw.seppuku.resolver.Inject;
import pw.seppuku.resolver.Resolver;

public final class SimpleResolver implements Resolver {

  private final Map<Class<?>, Object> dependencyClassToObjectMap;

  public SimpleResolver() {
    this(new HashMap<>());
  }

  public SimpleResolver(final Map<Class<?>, Object> dependencyClassToObjectMap) {
    this.dependencyClassToObjectMap = dependencyClassToObjectMap;
  }

  @Override
  public <T> T resolveDependency(final Class<T> dependencyType) {
    if (dependencyType == Resolver.class) {
      //noinspection unchecked
      return (T) this;
    }

    //noinspection unchecked
    return (T) dependencyClassToObjectMap.get(dependencyType);
  }

  @Override
  public <T> T resolveDependenciesAndCreate(final Class<T> type)
      throws InvocationTargetException, InstantiationException, IllegalAccessException {
    final var optimalConstructor = findOptimalConstructor(type);
    final var dependencies = resolveDependencies(optimalConstructor);
    return optimalConstructor.newInstance(dependencies);
  }

  private <T> Constructor<T> findOptimalConstructor(final Class<T> type) {
    final var allConstructors = findAllConstructors(type);
    final var sortedConstructors = sortConstructorsByDependenciesMet(allConstructors);
    System.out.println(sortedConstructors);

    return sortedConstructors.stream().findFirst().orElseThrow(RuntimeException::new);
  }

  private <T> List<Constructor<T>> findAllConstructors(final Class<T> type) {
    //noinspection unchecked
    return Arrays.stream(type.getConstructors())
        .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
        .map(constructor -> (Constructor<T>) constructor).toList();
  }

  private <T> List<Constructor<T>> sortConstructorsByDependenciesMet(
      final List<Constructor<T>> allConstructors) {
    final var sortedConstructors = allConstructors.stream()
        .sorted(Comparator.comparingInt(this::calculateDependenciesMet))
        .collect(Collectors.toList());

    Collections.reverse(sortedConstructors);

    return sortedConstructors;
  }

  private <T> int calculateDependenciesMet(final Constructor<T> constructor) {
    return Arrays.stream(constructor.getParameterTypes())
        .filter(dependencyClassToObjectMap::containsKey).toList().size();
  }

  private <T> Object[] resolveDependencies(final Constructor<T> constructor) {
    final var parameterTypes = constructor.getParameterTypes();
    return Arrays.stream(parameterTypes).map(this::resolveDependency).toArray();
  }
}
