package pw.seppuku.at;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.at.toggleable.features.AutoToolFeature;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.resolver.Inject;
import pw.seppuku.resolver.Resolver;

public final class SeppukuAutoToolPlugin extends AbstractPlugin {

  private final static UUID SEPPUKU_AUTO_TOOL_UNIQUE_IDENTIFIER = UUID.fromString(
      "7cbf40be-2767-430b-9051-c91a9cc253bd");
  private final static String SEPPUKU_AUTO_TOOL_HUMAN_IDENTIFIER = "seppuku-auto-tool";
  private final static Version SEPPUKU_AUTO_TOOL_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_AUTO_TOOL_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final Resolver resolver;
  private final FeatureRepository featureRepository;

  @Inject
  public SeppukuAutoToolPlugin(final Resolver resolver, final FeatureRepository featureRepository) {
    super(SEPPUKU_AUTO_TOOL_UNIQUE_IDENTIFIER, SEPPUKU_AUTO_TOOL_HUMAN_IDENTIFIER,
        SEPPUKU_AUTO_TOOL_VERSION, SEPPUKU_AUTO_TOOL_AUTHORS);
    this.resolver = resolver;
    this.featureRepository = featureRepository;
  }

  @Override
  public void load()
      throws InvocationTargetException, InstantiationException, IllegalAccessException, DuplicateUniqueIdentifierFeatureException {
    featureRepository.add(resolver.create(AutoToolFeature.class));
  }

  @Override
  public void unload() throws CouldNotBeFoundFeatureException {
    final var autoTool = featureRepository.findFeatureByClass(AutoToolFeature.class);
    featureRepository.remove(autoTool);
  }
}
