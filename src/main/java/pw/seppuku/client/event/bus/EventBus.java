package pw.seppuku.client.event.bus;

import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.listener.EventListener;

public interface EventBus<T extends Event> {

    <E extends T> void add(final Class<E> eventClass, final EventListener<E> eventListener);

    <E extends T> void remove(final Class<E> eventClass, final EventListener<E> eventListener);

    <E extends T> boolean publish(final Class<E> eventClass, final E event);

    <E extends T> boolean publish(final E event);
}
