package pw.seppuku.plugin;

import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.UUID;

public abstract class AbstractPlugin implements Plugin {

    private final UUID uniqueIdentifier;

    private final String humanIdentifier;

    private final Version version;

    private final List<Author> authors;

    public AbstractPlugin(final UUID uniqueIdentifier, final String humanIdentifier, final Version version, final List<Author> authors) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.humanIdentifier = humanIdentifier;
        this.version = version;
        this.authors = authors;
    }

    @Override
    public UUID uniqueIdentifier() {
        return uniqueIdentifier;
    }

    @Override
    public String humanIdentifier() {
        return humanIdentifier;
    }

    @Override
    public Version version() {
        return version;
    }

    @Override
    public List<Author> authors() {
        return authors;
    }
}
