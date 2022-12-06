package pw.seppuku.feature;

import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.UUID;

public abstract class AbstractFeature implements Feature {

    private final UUID uniqueIdentifier;

    private final String humanIdentifier;

    private final Version version;

    private final List<Author> authors;

    protected AbstractFeature(final UUID uniqueIdentifier, final String humanIdentifier, final Version version, final List<Author> authors) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.humanIdentifier = humanIdentifier;
        this.version = version;
        this.authors = authors;
    }

    @Override
    public final UUID uniqueIdentifier() {
        return uniqueIdentifier;
    }

    @Override
    public final String humanIdentifier() {
        return humanIdentifier;
    }

    @Override
    public final Version version() {
        return version;
    }

    @Override
    public final List<Author> authors() {
        return authors;
    }
}
