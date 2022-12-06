package pw.seppuku.client.di.exception;

public class DependencyInjectionException extends Exception {

    public DependencyInjectionException() {
        super();
    }

    public DependencyInjectionException(final String message) {
        super(message);
    }
}
