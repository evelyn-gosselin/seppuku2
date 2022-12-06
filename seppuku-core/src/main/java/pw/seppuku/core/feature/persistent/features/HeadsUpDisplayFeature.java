package pw.seppuku.core.feature.persistent.features;

import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.gui.GuiRenderEvent;
import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public final class HeadsUpDisplayFeature extends PersistentFeature {

    private static final UUID HEADS_UP_DISPLAY_UNIQUE_IDENTIFIER = UUID.fromString("e0c28491-5fc1-43cd-b302-5f5d98e7c1e0");
    private static final String HEADS_UP_DISPLAY_HUMAN_IDENTIFIER = "heads_up_display";
    private static final Version HEADS_UP_DISPLAY_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> HEADS_UP_DISPLAY_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    private final EventBus eventBus;

    private final EventSubscriber<GuiRenderEvent> guiRenderEventEventSubscriber;

    public HeadsUpDisplayFeature(final EventBus eventBus, final FeatureRepository featureRepository) {
        super(HEADS_UP_DISPLAY_UNIQUE_IDENTIFIER, HEADS_UP_DISPLAY_HUMAN_IDENTIFIER, HEADS_UP_DISPLAY_VERSION, HEADS_UP_DISPLAY_AUTHORS);
        this.eventBus = eventBus;

        this.guiRenderEventEventSubscriber = event -> {
            final var y = new AtomicInteger(2);
            featureRepository.stream()
                    .filter(ToggleableFeature.class::isInstance)
                    .map(ToggleableFeature.class::cast)
                    .filter(ToggleableFeature::isRunning)
                    .forEach(toggleableFeature -> {
                        event.gui().getFont().drawShadow(event.poseStack(), toggleableFeature.humanIdentifier(), 2, y.get(), 0xffffff);
                        y.addAndGet(2 + event.gui().getFont().lineHeight);
                    });

            return false;
        };
    }

    @Override
    public void load() {
        eventBus.subscribe(GuiRenderEvent.class, guiRenderEventEventSubscriber);
    }

    @Override
    public void unload() {
        eventBus.unsubscribe(GuiRenderEvent.class, guiRenderEventEventSubscriber);
    }
}
