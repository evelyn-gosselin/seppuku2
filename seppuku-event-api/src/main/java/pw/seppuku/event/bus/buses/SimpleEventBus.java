package pw.seppuku.event.bus.buses;

import java.util.HashMap;
import java.util.Map;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.publish.EventPublisher;
import pw.seppuku.event.publish.publishers.SimpleEventPublisher;
import pw.seppuku.event.subscribe.EventSubscriber;

public final class SimpleEventBus implements EventBus {

  private final Map<Class<?>, EventPublisher<?>> eventTypeToPublisherMap;

  public SimpleEventBus() {
    this(new HashMap<>());
  }

  public SimpleEventBus(final Map<Class<?>, EventPublisher<?>> eventTypeToPublisherMap) {
    this.eventTypeToPublisherMap = eventTypeToPublisherMap;
  }

  @Override
  public <T> void subscribe(final Class<T> eventType, final EventSubscriber<T> subscriber) {
    findOrCreateEventPublisher(eventType).subscribe(subscriber);
  }

  @Override
  public <T> void unsubscribe(final Class<T> eventType, final EventSubscriber<T> subscriber) {
    findOrCreateEventPublisher(eventType).unsubscribe(subscriber);
  }

  @Override
  public <T> boolean publish(final T eventToPublish) {
    //noinspection unchecked
    final Class<T> eventClass = (Class<T>) eventToPublish.getClass();
    return findOrCreateEventPublisher(eventClass).publish(eventToPublish);
  }

  private <T> EventPublisher<T> findOrCreateEventPublisher(final Class<T> eventType) {
    eventTypeToPublisherMap.putIfAbsent(eventType, new SimpleEventPublisher<>());

    //noinspection unchecked
    return (EventPublisher<T>) eventTypeToPublisherMap.get(eventType);
  }
}
