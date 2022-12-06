package pw.seppuku.client.event.listener;

import pw.seppuku.client.event.Event;

@FunctionalInterface
public interface EventListener<T extends Event> {

    boolean onPublication(final T event);
}
