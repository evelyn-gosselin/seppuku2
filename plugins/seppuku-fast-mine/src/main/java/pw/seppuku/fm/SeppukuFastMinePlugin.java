package pw.seppuku.fm;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.fm.toggleable.features.FastMineFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.resolver.Inject;
import pw.seppuku.resolver.Resolver;

public final class SeppukuFastMinePlugin extends AbstractPlugin {

  private final static UUID SEPPUKU_FAST_MINE_UNIQUE_IDENTIFIER = UUID.fromString(
      "f94b9e61-9c6c-4dbb-ae08-53fc4c4e2df4");
  private final static String SEPPUKU_FAST_MINE_HUMAN_IDENTIFIER = "seppuku-fast-mine";
  private final static Version SEPPUKU_FAST_MINE_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_FAST_MINE_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final Resolver resolver;
  private final FeatureRepository featureRepository;

  @Inject
  public SeppukuFastMinePlugin(final Resolver resolver, final FeatureRepository featureRepository) {
    super(SEPPUKU_FAST_MINE_UNIQUE_IDENTIFIER, SEPPUKU_FAST_MINE_HUMAN_IDENTIFIER,
        SEPPUKU_FAST_MINE_VERSION, SEPPUKU_FAST_MINE_AUTHORS);
    this.resolver = resolver;
    this.featureRepository = featureRepository;
  }

  @Override
  public void load()
      throws InvocationTargetException, InstantiationException, IllegalAccessException, DuplicateUniqueIdentifierFeatureException {
    featureRepository.add(resolver.create(FastMineFeature.class));
  }

  @Override
  public void unload() throws CouldNotBeFoundFeatureException {
    final var fastMine = featureRepository.findFeatureByClass(FastMineFeature.class);
    featureRepository.remove(fastMine);
  }
}
