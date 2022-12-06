package pw.seppuku.core;

import pw.seppuku.core.feature.persistent.features.HeadsUpDisplayFeature;
import pw.seppuku.core.feature.toggleable.features.SprintFeature;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.feature.Feature;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class SeppukuCorePlugin extends AbstractPlugin {

    private final static UUID SEPPUKU_CORE_UNIQUE_IDENTIFIER = UUID.fromString("db3f7237-e827-4f2c-afe0-ee800e853e56");
    private final static String SEPPUKU_CORE_HUMAN_IDENTIFIER = "seppuku-core";
    private final static Version SEPPUKU_CORE_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> SEPPUKU_CORE_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    private final List<Feature> features = new ArrayList<>();

    public SeppukuCorePlugin() {
        super(SEPPUKU_CORE_UNIQUE_IDENTIFIER, SEPPUKU_CORE_HUMAN_IDENTIFIER, SEPPUKU_CORE_VERSION, SEPPUKU_CORE_AUTHORS);
    }

    @Override
    public void load(final EventBus eventBus, final FeatureRepository featureRepository) throws DuplicateUniqueIdentifierFeatureException {
        features.addAll(List.of(
                new HeadsUpDisplayFeature(eventBus),
                new SprintFeature(eventBus)
        ));

        featureRepository.addAll(features);
    }

    @Override
    public void unload(final EventBus eventBus, final FeatureRepository featureRepository) {
        featureRepository.removeAll(features);

        features.stream()
                .filter(ToggleableFeature.class::isInstance)
                .map(ToggleableFeature.class::cast)
                .forEach(ToggleableFeature::onDeactivation);

        features.clear();
    }
}
