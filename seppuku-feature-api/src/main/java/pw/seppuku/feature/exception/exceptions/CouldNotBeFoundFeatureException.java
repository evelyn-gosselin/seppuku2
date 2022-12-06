package pw.seppuku.feature.exception.exceptions;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.exception.FeatureException;

public class CouldNotBeFoundFeatureException extends FeatureException {

    public CouldNotBeFoundFeatureException(final String triedToFindBy) {
        super("Could not find feature by " + triedToFindBy);
    }
}
