package pw.seppuku.event.publish;

import pw.seppuku.event.subscribe.EventSubscriber;

public interface EventPublisher<T> {

    void subscribe(final EventSubscriber<T> subscriber);

    void unsubscribe(final EventSubscriber<T> subscriber);

    boolean publish(final T event);
}
