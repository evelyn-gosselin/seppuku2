package pw.seppuku.ci;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.ci.execute.features.HelpFeature;
import pw.seppuku.ci.execute.features.PluginsFeature;
import pw.seppuku.ci.execute.features.ToggleFeature;
import pw.seppuku.ci.persistent.features.ChatInterfaceFeature;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.resolver.Inject;
import pw.seppuku.resolver.Resolver;

public final class SeppukuChatInterfacePlugin extends AbstractPlugin {

  private final static UUID CHAT_INTERFACE_UNIQUE_IDENTIFIER = UUID.fromString(
      "864c83ce-6ca1-4bde-a6d1-8cc08d3612f7");
  private final static String CHAT_INTERFACE_HUMAN_IDENTIFIER = "seppuku-chat-interface";
  private final static Version CHAT_INTERFACE_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> CHAT_INTERFACE_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final Resolver resolver;
  private final FeatureRepository featureRepository;

  @Inject
  public SeppukuChatInterfacePlugin(final Resolver resolver,
      final FeatureRepository featureRepository) {
    super(CHAT_INTERFACE_UNIQUE_IDENTIFIER, CHAT_INTERFACE_HUMAN_IDENTIFIER, CHAT_INTERFACE_VERSION,
        CHAT_INTERFACE_AUTHORS);
    this.resolver = resolver;
    this.featureRepository = featureRepository;
  }

  @Override
  public void load()
      throws InvocationTargetException, InstantiationException, IllegalAccessException, DuplicateUniqueIdentifierFeatureException {
    featureRepository.add(resolver.resolveDependenciesAndCreate(ChatInterfaceFeature.class));
    featureRepository.add(resolver.resolveDependenciesAndCreate(HelpFeature.class));
    featureRepository.add(resolver.resolveDependenciesAndCreate(PluginsFeature.class));
    featureRepository.add(resolver.resolveDependenciesAndCreate(ToggleFeature.class));
  }

  @Override
  public void unload() throws CouldNotBeFoundFeatureException {
    final var chatInterface = featureRepository.findFeatureByClass(ChatInterfaceFeature.class);
    featureRepository.remove(chatInterface);

    final var helpFeature = featureRepository.findFeatureByClass(HelpFeature.class);
    featureRepository.remove(helpFeature);

    final var pluginsFeature = featureRepository.findFeatureByClass(PluginsFeature.class);
    featureRepository.remove(pluginsFeature);

    final var toggleFeature = featureRepository.findFeatureByClass(ToggleFeature.class);
    featureRepository.remove(toggleFeature);
  }
}
