package pw.seppuku.vfly;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.feature.exception.FeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.resolver.Inject;
import pw.seppuku.resolver.Resolver;
import pw.seppuku.vfly.toggleable.features.VanillaFlyFeature;

public final class SeppukuVanillaFlyPlugin extends AbstractPlugin {

  private final static UUID SEPPUKU_VANILLA_FLY_UNIQUE_IDENTIFIER = UUID.fromString(
      "9be88cb8-83ba-491d-bd80-5ad3a7dd0c1b");
  private final static String SEPPUKU_VANILLA_FLY_HUMAN_IDENTIFIER = "seppuku-vanilla-fly";
  private final static Version SEPPUKU_VANILLA_FLY_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_VANILLA_FLY_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final Resolver resolver;
  private final FeatureRepository featureRepository;

  @Inject
  public SeppukuVanillaFlyPlugin(final Resolver resolver,
      final FeatureRepository featureRepository) {
    super(SEPPUKU_VANILLA_FLY_UNIQUE_IDENTIFIER, SEPPUKU_VANILLA_FLY_HUMAN_IDENTIFIER,
        SEPPUKU_VANILLA_FLY_VERSION, SEPPUKU_VANILLA_FLY_AUTHORS);
    this.resolver = resolver;
    this.featureRepository = featureRepository;
  }

  @Override
  public void load()
      throws InvocationTargetException, InstantiationException, IllegalAccessException, DuplicateUniqueIdentifierFeatureException {
    featureRepository.add(resolver.create(VanillaFlyFeature.class));
  }

  @Override
  public void unload() throws FeatureException {
    final var vanillaFly = featureRepository.findFeatureByClass(VanillaFlyFeature.class);
    featureRepository.remove(vanillaFly);
  }
}
