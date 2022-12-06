package pw.seppuku.event.publish.publishers;

import java.util.ArrayList;
import java.util.List;
import pw.seppuku.event.publish.EventPublisher;
import pw.seppuku.event.subscribe.EventSubscriber;

public final class SimpleEventPublisher<T> implements EventPublisher<T> {

  private final List<EventSubscriber<T>> eventSubscribers;

  public SimpleEventPublisher() {
    this(new ArrayList<>());
  }

  public SimpleEventPublisher(final List<EventSubscriber<T>> eventSubscribers) {
    this.eventSubscribers = eventSubscribers;
  }

  @Override
  public void subscribe(final EventSubscriber<T> eventSubscriberToAdd) {
    eventSubscribers.add(eventSubscriberToAdd);
  }

  @Override
  public void unsubscribe(final EventSubscriber<T> eventSubscriberToRemove) {
    eventSubscribers.remove(eventSubscriberToRemove);
  }

  @Override
  public boolean publish(final T eventToPublish) {
    // don't simplify by replacing `map` with `anyMatch` since
    // the predicate might not be evaluated for all elements

    //noinspection SimplifyStreamApiCallChains
    return eventSubscribers.stream()
        .map(eventSubscriber -> {
          try {
            return eventSubscriber.onPublication(eventToPublish);
          } catch (final Exception exception) {
            exception.printStackTrace();
            return false;
          }
        })
        .anyMatch(Boolean::booleanValue);
  }
}
