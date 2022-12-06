package pw.seppuku.feature.persistent;

import pw.seppuku.feature.AbstractFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.UUID;

public abstract class PersistentFeature extends AbstractFeature {

    protected PersistentFeature(final UUID uniqueIdentifier, final String humanIdentifier, final Version version, final List<Author> authors) {
        super(uniqueIdentifier, humanIdentifier, version, authors);
    }
}
