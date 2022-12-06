package pw.seppuku.client.event.publish.publishers;

import pw.seppuku.client.event.Event;
import pw.seppuku.client.event.listener.EventListener;
import pw.seppuku.client.event.publish.EventPublisher;

import java.util.ArrayList;
import java.util.List;

public final class SimpleEventPublisher<T extends Event> implements EventPublisher<T> {

    private final List<EventListener<T>> eventListeners;

    public SimpleEventPublisher() {
        this(new ArrayList<>());
    }

    public SimpleEventPublisher(final List<EventListener<T>> eventListeners) {
        this.eventListeners = eventListeners;
    }

    @Override
    public boolean add(final EventListener<T> eventListener) {
        return this.eventListeners.add(eventListener);
    }

    @Override
    public boolean remove(final EventListener<T> eventListener) {
        return eventListeners.remove(eventListener);
    }

    @Override
    public boolean publish(final T event) {
        //noinspection SimplifyStreamApiCallChains
        return eventListeners.stream()
                .map(eventListener -> eventListener.onPublication(event))
                .anyMatch(Boolean::booleanValue);
    }
}
