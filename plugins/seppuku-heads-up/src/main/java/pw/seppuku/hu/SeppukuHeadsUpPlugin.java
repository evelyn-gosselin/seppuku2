package pw.seppuku.hu;

import pw.seppuku.event.bus.EventBus;
import pw.seppuku.feature.exception.FeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.hu.persistent.features.HeadsUpFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class SeppukuHeadsUpPlugin extends AbstractPlugin {

    private final static UUID SEPPUKU_HEADS_UP_UNIQUE_IDENTIFIER = UUID.fromString("8e68c20b-7803-4327-b295-3d3feb6e01b3");
    private final static String SEPPUKU_HEADS_UP_HUMAN_IDENTIFIER = "seppuku-heads-up";
    private final static Version SEPPUKU_HEADS_UP_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> SEPPUKU_HEADS_UP_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    public SeppukuHeadsUpPlugin() {
        super(SEPPUKU_HEADS_UP_UNIQUE_IDENTIFIER, SEPPUKU_HEADS_UP_HUMAN_IDENTIFIER, SEPPUKU_HEADS_UP_VERSION, SEPPUKU_HEADS_UP_AUTHORS);
    }

    @Override
    public void load(final EventBus eventBus, final FeatureRepository featureRepository) throws FeatureException {
        final var headsUp = new HeadsUpFeature(eventBus, featureRepository);
        featureRepository.add(headsUp);
    }

    @Override
    public void unload(final EventBus eventBus, final FeatureRepository featureRepository) throws FeatureException {
        final var headsUp = featureRepository.findFeatureByClass(HeadsUpFeature.class);
        featureRepository.remove(headsUp);
    }
}
