package pw.seppuku.client.feature.persistent;

import pw.seppuku.core.SeppukuCorePlugin;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.events.SeppukuEventsPlugin;
import pw.seppuku.feature.exception.FeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.Plugin;
import pw.seppuku.plugin.exception.exceptions.DuplicateUniqueIdentifierPluginException;
import pw.seppuku.plugin.repository.PluginRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class PluginLoaderFeature extends PersistentFeature {

    private static final UUID PLUGIN_LOADER_UNIQUE_IDENTIFIER = UUID.fromString("77e24308-d103-46a9-bb13-e97b7b08062f");
    private static final String PLUGIN_LOADER_HUMAN_IDENTIFIER = "plugin_loader";
    private static final Version PLUGIN_LOADER_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> PLUGIN_LOADER_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    private final EventBus eventBus;
    private final FeatureRepository featureRepository;
    private final PluginRepository pluginRepository;

    public PluginLoaderFeature(final EventBus eventBus, final FeatureRepository featureRepository, final PluginRepository pluginRepository) {
        super(PLUGIN_LOADER_UNIQUE_IDENTIFIER, PLUGIN_LOADER_HUMAN_IDENTIFIER, PLUGIN_LOADER_VERSION, PLUGIN_LOADER_AUTHORS);
        this.eventBus = eventBus;
        this.featureRepository = featureRepository;
        this.pluginRepository = pluginRepository;
    }

    @Override
    public void load() {
        addBundledEventsPlugin();
        addBundledCorePlugin();
        // TODO: Load plugin jar and zip files

        for (final var plugin : pluginRepository) {
            try {
                plugin.load(eventBus, featureRepository);
            } catch (final FeatureException exception) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    public void unload() {
        for (final var plugin : pluginRepository) {
            try {
                plugin.unload(eventBus, featureRepository);
            } catch (final FeatureException exception) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        }
    }

    private void addBundledEventsPlugin() {
        final var seppukuEventsPlugin = new SeppukuEventsPlugin();

        try {
            pluginRepository.add(seppukuEventsPlugin);
        } catch (final DuplicateUniqueIdentifierPluginException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    private void addBundledCorePlugin() {
        final var seppukuCorePlugin = new SeppukuCorePlugin();

        try {
            pluginRepository.add(seppukuCorePlugin);
        } catch (final DuplicateUniqueIdentifierPluginException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }
}
