package pw.seppuku.feature.exception;

public abstract class FeatureException extends Exception {

  protected FeatureException(final String message) {
    super(message);
  }
}
