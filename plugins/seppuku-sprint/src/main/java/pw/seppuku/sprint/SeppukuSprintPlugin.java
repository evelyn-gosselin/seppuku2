package pw.seppuku.sprint;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.resolver.Resolver;
import pw.seppuku.sprint.toggleable.features.SprintFeature;

public final class SeppukuSprintPlugin extends AbstractPlugin {

  private final static UUID SEPPUKU_SPRINT_UNIQUE_IDENTIFIER = UUID.fromString(
      "1a20d797-6e33-4e26-a7dd-c8e7cc33ca3e");
  private final static String SEPPUKU_SPRINT_HUMAN_IDENTIFIER = "seppuku-sprint";
  private final static Version SEPPUKU_SPRINT_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_SPRINT_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final Resolver resolver;
  private final FeatureRepository featureRepository;

  public SeppukuSprintPlugin(final Resolver resolver, final FeatureRepository featureRepository) {
    super(SEPPUKU_SPRINT_UNIQUE_IDENTIFIER, SEPPUKU_SPRINT_HUMAN_IDENTIFIER, SEPPUKU_SPRINT_VERSION,
        SEPPUKU_SPRINT_AUTHORS);
    this.resolver = resolver;
    this.featureRepository = featureRepository;
  }

  @Override
  public void load()
      throws InvocationTargetException, InstantiationException, IllegalAccessException, DuplicateUniqueIdentifierFeatureException {
    featureRepository.add(resolver.resolveDependenciesAndCreate(SprintFeature.class));
  }

  @Override
  public void unload() throws CouldNotBeFoundFeatureException {
    final var sprint = featureRepository.findFeatureByClass(SprintFeature.class);
    featureRepository.remove(sprint);
  }
}
