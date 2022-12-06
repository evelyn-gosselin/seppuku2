package pw.seppuku.ci.execute.features;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.execute.ExecutableFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class ToggleFeature extends ExecutableFeature {
    private static final UUID TOGGLE_UNIQUE_IDENTIFIER = UUID.fromString("356e6b42-c3f8-4b73-986b-e3a5323fd57b");
    private static final String TOGGLE_HUMAN_IDENTIFIER = "toggle";
    private static final Version TOGGLE_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> TOGGLE_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    private final FeatureRepository featureRepository;

    public ToggleFeature(final FeatureRepository featureRepository) {
        super(TOGGLE_UNIQUE_IDENTIFIER, TOGGLE_HUMAN_IDENTIFIER, TOGGLE_VERSION, TOGGLE_AUTHORS);
        this.featureRepository = featureRepository;
    }

    @Override
    public void execute(final String... rest) {
        final var humanIdentifier = rest[0];
        final var toggleableFeature = featureRepository.findFeaturesByHumanIdentifier(humanIdentifier, ToggleableFeature.class).stream()
                .findFirst()
                .orElseThrow(RuntimeException::new);

        toggleableFeature.setRunning(!toggleableFeature.isRunning());
    }

    @Override
    public void load() {}

    @Override
    public void unload() {}
}
