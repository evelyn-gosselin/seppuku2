package pw.seppuku.ci;

import pw.seppuku.ci.execute.features.HelpFeature;
import pw.seppuku.ci.execute.features.ToggleFeature;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.feature.exception.FeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.ci.persistent.features.ChatInterfaceFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class SeppukuChatInterfacePlugin extends AbstractPlugin {

    private final static UUID CHAT_INTERFACE_UNIQUE_IDENTIFIER = UUID.fromString("864c83ce-6ca1-4bde-a6d1-8cc08d3612f7");
    private final static String CHAT_INTERFACE_HUMAN_IDENTIFIER = "seppuku-chat-interface";
    private final static Version CHAT_INTERFACE_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> CHAT_INTERFACE_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    public SeppukuChatInterfacePlugin() {
        super(CHAT_INTERFACE_UNIQUE_IDENTIFIER, CHAT_INTERFACE_HUMAN_IDENTIFIER, CHAT_INTERFACE_VERSION, CHAT_INTERFACE_AUTHORS);
    }

    @Override
    public void load(final EventBus eventBus, final FeatureRepository featureRepository) throws FeatureException {
        final var chatInterface = new ChatInterfaceFeature(eventBus, featureRepository);
        featureRepository.add(chatInterface);

        final var helpFeature = new HelpFeature(featureRepository);
        featureRepository.add(helpFeature);

        final var toggleFeature = new ToggleFeature(featureRepository);
        featureRepository.add(toggleFeature);
    }

    @Override
    public void unload(final EventBus eventBus, final FeatureRepository featureRepository) throws FeatureException {
        final var chatInterface = featureRepository.findFeatureByClass(ChatInterfaceFeature.class);
        featureRepository.remove(chatInterface);

        final var helpFeature = featureRepository.findFeatureByClass(HelpFeature.class);
        featureRepository.remove(helpFeature);

        final var toggleFeature = featureRepository.findFeatureByClass(ToggleFeature.class);
        featureRepository.remove(toggleFeature);
    }
}
