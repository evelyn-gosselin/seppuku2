package pw.seppuku.client.feature.toggleable.features;

import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class SprintFeature extends ToggleableFeature {

    private static final UUID SPRINT_UNIQUE_IDENTIFIER = UUID.fromString("5746740b-a687-4018-8cd4-b8dc5a1b28ab");
    private static final String SPRINT_HUMAN_IDENTIFIER = "sprint";
    private static final Version SPRINT_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> SPRINT_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    public SprintFeature() {
        super(SPRINT_UNIQUE_IDENTIFIER, SPRINT_HUMAN_IDENTIFIER, SPRINT_VERSION, SPRINT_AUTHORS);
    }

    @Override
    protected void onActivation() {}

    @Override
    protected void onDeactivation() {}
}
