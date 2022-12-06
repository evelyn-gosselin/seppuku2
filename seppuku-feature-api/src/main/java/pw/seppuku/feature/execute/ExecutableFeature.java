package pw.seppuku.feature.execute;

import pw.seppuku.feature.AbstractFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.UUID;

// TODO: Rework the entire ExecutableFeature system - dash-foo or brigadier?
public abstract class ExecutableFeature extends AbstractFeature {
    protected ExecutableFeature(final UUID uniqueIdentifier, final String humanIdentifier, final Version version, final List<Author> authors) {
        super(uniqueIdentifier, humanIdentifier, version, authors);
    }

    public abstract void execute(final String... rest) throws Exception;
}
