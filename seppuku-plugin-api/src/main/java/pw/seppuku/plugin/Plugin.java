package pw.seppuku.plugin;

import java.util.List;
import java.util.UUID;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.feature.exception.FeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.repository.PluginRepository;

public interface Plugin {

  UUID uniqueIdentifier();

  String humanIdentifier();

  Version version();

  List<Author> authors();

  void load(final EventBus eventBus, final FeatureRepository featureRepository,
      final PluginRepository pluginRepository) throws FeatureException;

  void unload(final EventBus eventBus, final FeatureRepository featureRepository,
      final PluginRepository pluginRepository) throws FeatureException;
}
