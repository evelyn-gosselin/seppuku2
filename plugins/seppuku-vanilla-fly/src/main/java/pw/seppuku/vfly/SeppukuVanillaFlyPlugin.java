package pw.seppuku.vfly;

import pw.seppuku.event.bus.EventBus;
import pw.seppuku.feature.exception.FeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.vfly.toggleable.features.VanillaFlyFeature;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class SeppukuVanillaFlyPlugin extends AbstractPlugin {

    private final static UUID SEPPUKU_VANILLA_FLY_UNIQUE_IDENTIFIER = UUID.fromString("9be88cb8-83ba-491d-bd80-5ad3a7dd0c1b");
    private final static String SEPPUKU_VANILLA_FLY_HUMAN_IDENTIFIER = "seppuku-vanilla-fly";
    private final static Version SEPPUKU_VANILLA_FLY_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> SEPPUKU_VANILLA_FLY_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    public SeppukuVanillaFlyPlugin() {
        super(SEPPUKU_VANILLA_FLY_UNIQUE_IDENTIFIER, SEPPUKU_VANILLA_FLY_HUMAN_IDENTIFIER, SEPPUKU_VANILLA_FLY_VERSION, SEPPUKU_VANILLA_FLY_AUTHORS);
    }

    @Override
    public void load(final EventBus eventBus, final FeatureRepository featureRepository) throws FeatureException {
        final var vanillaFly = new VanillaFlyFeature(eventBus);
        featureRepository.add(vanillaFly);
    }

    @Override
    public void unload(final EventBus eventBus, final FeatureRepository featureRepository) throws FeatureException {
        final var vanillaFly = featureRepository.findFeatureByClass(VanillaFlyFeature.class);
        featureRepository.remove(vanillaFly);
    }
}
