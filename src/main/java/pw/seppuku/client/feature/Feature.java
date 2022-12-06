package pw.seppuku.client.feature;

import pw.seppuku.client.feature.exception.FeatureException;

import java.util.UUID;

public interface Feature {

    UUID uniqueIdentifier();

    String humanIdentifier();

    String description();

    String author();

    boolean running();

    void setRunning(final boolean running) throws FeatureException;
}
