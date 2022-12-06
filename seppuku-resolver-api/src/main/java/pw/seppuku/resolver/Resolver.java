package pw.seppuku.resolver;

import java.lang.reflect.InvocationTargetException;

public interface Resolver {

  <T> T resolveDependency(final Class<T> dependencyType);

  <T> T resolveDependenciesAndCreate(final Class<T> type)
      throws InvocationTargetException, InstantiationException, IllegalAccessException;
}
