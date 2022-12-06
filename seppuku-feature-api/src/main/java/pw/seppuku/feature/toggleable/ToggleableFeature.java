package pw.seppuku.feature.toggleable;

import pw.seppuku.feature.AbstractFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.UUID;

public abstract class ToggleableFeature extends AbstractFeature {

    private boolean running;

    protected ToggleableFeature(final UUID uniqueIdentifier, final String humanIdentifier, final Version version, final List<Author> authors) {
        super(uniqueIdentifier, humanIdentifier, version, authors);
    }

    protected abstract void onActivation();

    protected abstract void onDeactivation();

    public final boolean isRunning() {
        return running;
    }

    public final void setRunning(final boolean running) {
        this.running = running;

        if (running) {
            onActivation();
        } else {
            onDeactivation();
        }
    }
}
