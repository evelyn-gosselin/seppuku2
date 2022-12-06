package pw.seppuku.core.feature.persistent.features;

import pw.seppuku.event.bus.EventBus;
import pw.seppuku.events.minecraft.client.gui.GuiRenderEvent;
import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class HeadsUpDisplayFeature extends PersistentFeature {

    private static final UUID HEADS_UP_DISPLAY_UNIQUE_IDENTIFIER = UUID.fromString("e0c28491-5fc1-43cd-b302-5f5d98e7c1e0");
    private static final String HEADS_UP_DISPLAY_HUMAN_IDENTIFIER = "heads_up_display";
    private static final Version HEADS_UP_DISPLAY_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> HEADS_UP_DISPLAY_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    public HeadsUpDisplayFeature(final EventBus eventBus) {
        super(HEADS_UP_DISPLAY_UNIQUE_IDENTIFIER, HEADS_UP_DISPLAY_HUMAN_IDENTIFIER, HEADS_UP_DISPLAY_VERSION, HEADS_UP_DISPLAY_AUTHORS);
        eventBus.subscribe(GuiRenderEvent.class, event -> {
            event.gui().getFont().drawShadow(event.poseStack(), "Seppuku", 2, 2, 0xffffff);
            return false;
        });
    }
}
