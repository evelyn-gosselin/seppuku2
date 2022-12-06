package pw.seppuku.client.event.publish;

import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.listener.EventListener;

public interface EventPublisher<T extends Event> {

    boolean add(final EventListener<T> eventListener);

    boolean remove(final EventListener<T> eventListener);

    boolean publish(final T event);
}
