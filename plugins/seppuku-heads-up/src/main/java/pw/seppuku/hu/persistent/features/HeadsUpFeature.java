package pw.seppuku.hu.persistent.features;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.gui.GuiRenderEvent;
import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

public final class HeadsUpFeature extends PersistentFeature {

  private static final UUID HEADS_UP_UNIQUE_IDENTIFIER = UUID.fromString(
      "e5c78375-9c81-478d-ab85-7bf66d06ce75");
  private static final String HEADS_UP_HUMAN_IDENTIFIER = "heads-up";
  private static final Version HEADS_UP_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> HEADS_UP_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final EventBus eventBus;

  private final EventSubscriber<GuiRenderEvent> guiRenderEventSubscriber;

  public HeadsUpFeature(final EventBus eventBus, final FeatureRepository featureRepository) {
    super(HEADS_UP_UNIQUE_IDENTIFIER, HEADS_UP_HUMAN_IDENTIFIER, HEADS_UP_VERSION,
        HEADS_UP_AUTHORS);
    this.eventBus = eventBus;

    this.guiRenderEventSubscriber = event -> {
      final var y = new AtomicInteger(2);
      featureRepository.stream()
          .filter(ToggleableFeature.class::isInstance)
          .map(ToggleableFeature.class::cast)
          .filter(ToggleableFeature::isRunning)
          .forEach(toggleableFeature -> {
            event.gui().getFont()
                .drawShadow(event.poseStack(), toggleableFeature.humanIdentifier(), 2, y.get(),
                    0xffffff);
            y.addAndGet(2 + event.gui().getFont().lineHeight);
          });

      return false;
    };
  }

  @Override
  public void load() {
    eventBus.subscribe(GuiRenderEvent.class, guiRenderEventSubscriber);
  }

  @Override
  public void unload() {
    eventBus.unsubscribe(GuiRenderEvent.class, guiRenderEventSubscriber);
  }
}
