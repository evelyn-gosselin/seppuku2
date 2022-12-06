package pw.seppuku.plugin.repository;

import pw.seppuku.plugin.Plugin;
import pw.seppuku.plugin.exception.exceptions.DuplicateUniqueIdentifierPluginException;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface PluginRepository extends Iterable<Plugin> {
    <T extends Plugin> void add(final T pluginToAdd) throws DuplicateUniqueIdentifierPluginException;

    <T extends Plugin> void remove(final T pluginToRemove);

    <T extends Plugin> void addAll(final Collection<? extends T> pluginsToAdd) throws DuplicateUniqueIdentifierPluginException;

    <T extends Plugin> void removeAll(final Collection<? extends T> pluginsToRemove);

    default Stream<Plugin> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    default Stream<Plugin> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }
}
