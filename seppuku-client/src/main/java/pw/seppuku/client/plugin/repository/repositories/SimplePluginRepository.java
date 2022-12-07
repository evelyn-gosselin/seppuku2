package pw.seppuku.client.plugin.repository.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import pw.seppuku.plugin.Plugin;
import pw.seppuku.plugin.exception.exceptions.DuplicateUniqueIdentifierPluginException;
import pw.seppuku.plugin.repository.PluginRepository;

public final class SimplePluginRepository implements PluginRepository {

  private final List<Plugin> plugins;
  private final Set<UUID> presentUniqueIdentifiers;

  public SimplePluginRepository() {
    this(new ArrayList<>(), new HashSet<>());
  }

  public SimplePluginRepository(final List<Plugin> plugins,
      final Set<UUID> presentUniqueIdentifiers) {
    this.plugins = plugins;
    this.presentUniqueIdentifiers = presentUniqueIdentifiers;
  }

  @Override
  public <T extends Plugin> void add(final T pluginToAdd)
      throws DuplicateUniqueIdentifierPluginException {
    if (presentUniqueIdentifiers.contains(pluginToAdd.uniqueIdentifier())) {
      throw new DuplicateUniqueIdentifierPluginException(pluginToAdd.uniqueIdentifier());
    }

    presentUniqueIdentifiers.add(pluginToAdd.uniqueIdentifier());
    plugins.add(pluginToAdd);
  }

  @Override
  public <T extends Plugin> void remove(final T pluginToRemove) {
    presentUniqueIdentifiers.remove(pluginToRemove.uniqueIdentifier());
    plugins.remove(pluginToRemove);
  }

  @Override
  public <T extends Plugin> void addAll(final Collection<? extends T> pluginsToAdd)
      throws DuplicateUniqueIdentifierPluginException {
    for (final var pluginToAdd : pluginsToAdd) {
      add(pluginToAdd);
    }
  }

  @Override
  public <T extends Plugin> void removeAll(final Collection<? extends T> pluginsToRemove) {
    for (final var pluginToRemove : pluginsToRemove) {
      remove(pluginToRemove);
    }
  }

  @Override
  public Iterator<Plugin> iterator() {
    return plugins.iterator();
  }
}
