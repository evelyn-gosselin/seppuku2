package pw.seppuku.ci.execute.features;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import pw.seppuku.feature.execute.ExecutableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.repository.PluginRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public final class PluginsFeature extends ExecutableFeature {

    private static final UUID PLUGINS_UNIQUE_IDENTIFIER = UUID.fromString("ecc7bf3e-7195-44ac-84da-b1f1f2320856");
    private static final String PLUGINS_HUMAN_IDENTIFIER = "plugins";
    private static final Version PLUGINS_VERSION = new Version(0, 1, 0, Optional.empty(), Optional.empty());
    private static final List<Author> PLUGINS_AUTHORS = List.of(new Author("wine", Optional.of("Ossian"), Optional.of("Winter"), Optional.of("ossian@hey.com")));

    private final PluginRepository pluginRepository;

    public PluginsFeature(final PluginRepository pluginRepository) {
        super(PLUGINS_UNIQUE_IDENTIFIER, PLUGINS_HUMAN_IDENTIFIER, PLUGINS_VERSION, PLUGINS_AUTHORS);
        this.pluginRepository = pluginRepository;
    }

    @Override
    public void execute(final String... rest) {
        final var literalContent = pluginRepository.stream()
                .map(plugin -> plugin.humanIdentifier() + " " + plugin.version())
                .collect(Collectors.joining("\n"));

        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(literalContent));
    }

    @Override
    public void load() {
    }

    @Override
    public void unload() {
    }
}
