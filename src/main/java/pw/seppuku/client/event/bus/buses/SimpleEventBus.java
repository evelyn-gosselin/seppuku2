package pw.seppuku.client.event.bus.buses;

import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.bus.EventBus;
import pw.seppuku.client.event.listener.EventListener;
import pw.seppuku.client.event.publish.EventPublisher;
import pw.seppuku.client.event.publish.publishers.SimpleEventPublisher;

import java.util.HashMap;
import java.util.Map;

public final class SimpleEventBus<T extends Event> implements EventBus<T> {

    private final Map<Class<? extends T>, EventPublisher<? extends T>> eventClassPublisherMap;

    public SimpleEventBus() {
        this(new HashMap<>());
    }

    public SimpleEventBus(Map<Class<? extends T>, EventPublisher<? extends T>> eventClassPublisherMap) {
        this.eventClassPublisherMap = eventClassPublisherMap;
    }

    @Override
    public <E extends T> void add(final Class<E> eventClass, final EventListener<E> eventListener) {
        findOrCreateEventPublisher(eventClass).add(eventListener);
    }

    @Override
    public <E extends T> void remove(final Class<E> eventClass, final EventListener<E> eventListener) {
        findOrCreateEventPublisher(eventClass).remove(eventListener);
    }

    @Override
    public <E extends T> boolean publish(final Class<E> eventClass, final E event) {
        return findOrCreateEventPublisher(eventClass).publish(event);
    }

    @Override
    public <E extends T> boolean publish(final E event) {
        //noinspection unchecked
        return publish((Class<E>) event.getClass(), event);
    }

    private <E extends T> EventPublisher<E> findOrCreateEventPublisher(final Class<E> eventClass) {
        if (!eventClassPublisherMap.containsKey(eventClass))
            eventClassPublisherMap.put(eventClass, new SimpleEventPublisher<>());

        //noinspection unchecked
        return (EventPublisher<E>) eventClassPublisherMap.get(eventClass);
    }
}
