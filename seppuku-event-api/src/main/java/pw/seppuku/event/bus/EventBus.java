package pw.seppuku.event.bus;

import pw.seppuku.event.subscribe.EventSubscriber;

public interface EventBus {

    <T> void subscribe(final Class<T> eventClass, final EventSubscriber<T> subscriber);

    <T> void unsubscribe(final Class<T> eventClass, final EventSubscriber<T> subscriber);

    <T> boolean publish(final T event);
}
