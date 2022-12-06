package pw.seppuku.event.subscribe;

@FunctionalInterface
public interface EventSubscriber<T> {

    boolean onPublication(final T event) throws Exception;
}
