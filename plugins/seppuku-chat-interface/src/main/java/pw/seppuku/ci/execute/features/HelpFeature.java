package pw.seppuku.ci.execute.features;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.execute.ExecutableFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class HelpFeature extends ExecutableFeature {
    private static final UUID HELP_UNIQUE_IDENTIFIER = UUID.fromString("1f7a6dd2-20bb-4843-8e3a-2f75454e2e50");
    private static final String HELP_HUMAN_IDENTIFIER = "help";
    private static final Version HELP_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> HELP_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    private final FeatureRepository featureRepository;

    public HelpFeature(final FeatureRepository featureRepository) {
        super(HELP_UNIQUE_IDENTIFIER, HELP_HUMAN_IDENTIFIER, HELP_VERSION, HELP_AUTHORS);
        this.featureRepository = featureRepository;
    }

    @Override
    public void execute(final String... rest) throws CouldNotBeFoundFeatureException {
        final var humanIdentifier = rest[0];
        final var feature = featureRepository.findFeaturesByHumanIdentifier(humanIdentifier, Feature.class).stream()
                .findFirst()
                .orElseThrow(() -> new CouldNotBeFoundFeatureException(humanIdentifier));

        // TODO: Sprint to chat
        System.out.println(feature.uniqueIdentifier());
        System.out.println(feature.humanIdentifier());
        System.out.println(feature.version());
        System.out.println(feature.authors());
    }

    @Override
    public void load() {}

    @Override
    public void unload() {}
}
