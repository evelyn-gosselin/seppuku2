package pw.seppuku.plugin.repository;

import pw.seppuku.plugin.Plugin;
import pw.seppuku.plugin.exception.exceptions.DuplicateUniqueIdentifierPluginException;

import java.util.Collection;

public interface PluginRepository {
    <T extends Plugin> void add(final T pluginToAdd) throws DuplicateUniqueIdentifierPluginException;

    <T extends Plugin> void remove(final T pluginToRemove);

    <T extends Plugin> void addAll(final Collection<? extends T> pluginsToAdd) throws DuplicateUniqueIdentifierPluginException;

    <T extends Plugin> void removeAll(final Collection<? extends T> pluginsToRemove);
}
