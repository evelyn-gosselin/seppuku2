package pw.seppuku.events;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.plugin.repository.PluginRepository;

public final class SeppukuEventsPlugin extends AbstractPlugin {

  private static final UUID SEPPUKU_EVENTS_UNIQUE_IDENTIFIER = UUID.fromString(
      "7d61af53-2469-4f01-a0fd-2af421e1380e");
  private final static String SEPPUKU_EVENTS_HUMAN_IDENTIFIER = "seppuku-events";
  private final static Version SEPPUKU_EVENTS_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_EVENTS_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  public SeppukuEventsPlugin() {
    super(SEPPUKU_EVENTS_UNIQUE_IDENTIFIER, SEPPUKU_EVENTS_HUMAN_IDENTIFIER, SEPPUKU_EVENTS_VERSION,
        SEPPUKU_EVENTS_AUTHORS);
  }

  @Override
  public void load(final EventBus eventBus, final FeatureRepository featureRepository,
      final PluginRepository pluginRepository) {
  }

  @Override
  public void unload(final EventBus eventBus, final FeatureRepository featureRepository,
      final PluginRepository pluginRepository) {
  }
}
