package pw.seppuku.af;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.af.toggleable.features.AutoFarmFeature;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.resolver.Inject;
import pw.seppuku.resolver.Resolver;

public final class SeppukuAutoFarmPlugin extends AbstractPlugin {

  private final static UUID SEPPUKU_AUTO_FARM_UNIQUE_IDENTIFIER = UUID.fromString(
      "24c948dc-6b6a-4ddd-b5bb-dc8a5025d561");
  private final static String SEPPUKU_AUTO_FARM_HUMAN_IDENTIFIER = "seppuku-auto-farm";
  private final static Version SEPPUKU_AUTO_FARM_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_AUTO_FARM_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final Resolver resolver;
  private final FeatureRepository featureRepository;

  @Inject
  public SeppukuAutoFarmPlugin(final Resolver resolver, final FeatureRepository featureRepository) {
    super(SEPPUKU_AUTO_FARM_UNIQUE_IDENTIFIER, SEPPUKU_AUTO_FARM_HUMAN_IDENTIFIER,
        SEPPUKU_AUTO_FARM_VERSION, SEPPUKU_AUTO_FARM_AUTHORS);
    this.resolver = resolver;
    this.featureRepository = featureRepository;
  }

  @Override
  public void load()
      throws InvocationTargetException, InstantiationException, IllegalAccessException, DuplicateUniqueIdentifierFeatureException {
    featureRepository.add(resolver.create(AutoFarmFeature.class));
  }

  @Override
  public void unload() throws CouldNotBeFoundFeatureException {
    final var autoFarm = featureRepository.findFeatureByClass(AutoFarmFeature.class);
    featureRepository.remove(autoFarm);
  }
}
