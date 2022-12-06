package pw.seppuku.event.bus.buses;

import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.publish.EventPublisher;
import pw.seppuku.event.publish.publishers.SimpleEventPublisher;
import pw.seppuku.event.subscribe.EventSubscriber;

import java.util.HashMap;
import java.util.Map;

public final class SimpleEventBus implements EventBus {

    private final Map<Class<?>, EventPublisher<?>> eventClassToPublisherMap;

    public SimpleEventBus() {
        this(new HashMap<>());
    }

    public SimpleEventBus(final Map<Class<?>, EventPublisher<?>> eventClassToPublisherMap) {
        this.eventClassToPublisherMap = eventClassToPublisherMap;
    }

    @Override
    public <T> void subscribe(final Class<T> eventClass, final EventSubscriber<T> subscriber) {
        findOrCreateEventPublisher(eventClass).subscribe(subscriber);
    }

    @Override
    public <T> void unsubscribe(final Class<T> eventClass, final EventSubscriber<T> subscriber) {
        findOrCreateEventPublisher(eventClass).unsubscribe(subscriber);
    }

    @Override
    public <T> boolean publish(final T eventToPublish) {
        //noinspection unchecked
        final Class<T> eventClass = (Class<T>) eventToPublish.getClass();
        return findOrCreateEventPublisher(eventClass).publish(eventToPublish);
    }

    private <T> EventPublisher<T> findOrCreateEventPublisher(final Class<T> eventClass) {
        eventClassToPublisherMap.putIfAbsent(eventClass, new SimpleEventPublisher<>());

        //noinspection unchecked
        return (EventPublisher<T>) eventClassToPublisherMap.get(eventClass);
    }
}
