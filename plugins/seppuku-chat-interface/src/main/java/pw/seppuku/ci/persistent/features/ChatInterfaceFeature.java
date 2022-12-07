package pw.seppuku.ci.persistent.features;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.event.subscribe.EventSubscriber;
import pw.seppuku.events.minecraft.client.gui.screens.ChatScreenHandleInputEvent;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.execute.ExecutableFeature;
import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.resolver.Inject;
import pw.seppuku.transform.bundle.TransformerBundle;

public final class ChatInterfaceFeature extends PersistentFeature {

  private static final UUID CHAT_INTERFACE_UNIQUE_IDENTIFIER = UUID.fromString(
      "0fb128d8-5203-4665-9adb-f35f052896dc");
  private static final String CHAT_INTERFACE_HUMAN_IDENTIFIER = "chat-interface";
  private static final Version CHAT_INTERFACE_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> CHAT_INTERFACE_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private static final String CHAT_INTERFACE_PREFIX = ".";

  private final EventBus eventBus;

  private final EventSubscriber<ChatScreenHandleInputEvent> chatScreenHandleInputEventSubscriber;

  @Inject
  public ChatInterfaceFeature(final EventBus eventBus, final TransformerBundle transformerBundle) {
    super(CHAT_INTERFACE_UNIQUE_IDENTIFIER, CHAT_INTERFACE_HUMAN_IDENTIFIER, CHAT_INTERFACE_VERSION,
        CHAT_INTERFACE_AUTHORS);
    this.eventBus = eventBus;

    this.chatScreenHandleInputEventSubscriber = event -> {
      if (!event.message().startsWith(CHAT_INTERFACE_PREFIX)) {
        return false;
      }

      final var command = stripPrefixFromMessage(event.message());
      final var commandParts = splitCommandToCommandParts(command);
      final var commandArguments = splitCommandPartsToCommandArguments(commandParts);

      final var executableFeatureHumanIdentifier = commandParts.get(0);
      final var executableFeature = transformerBundle.transform(String.class,
          ExecutableFeature.class, executableFeatureHumanIdentifier);

      executableFeature.execute(commandArguments.toArray(String[]::new));

      return true;
    };
  }

  private String stripPrefixFromMessage(final String message) {
    return message.replaceFirst(CHAT_INTERFACE_PREFIX, "");
  }

  private List<String> splitCommandToCommandParts(final String command) {
    return Arrays.stream(command.split(" ")).toList();
  }

  private List<String> splitCommandPartsToCommandArguments(final List<String> commandParts) {
    return commandParts.stream().skip(1).toList();
  }

  @Override
  public void load() {
    eventBus.subscribe(ChatScreenHandleInputEvent.class, chatScreenHandleInputEventSubscriber);
  }

  @Override
  public void unload() {
    eventBus.unsubscribe(ChatScreenHandleInputEvent.class, chatScreenHandleInputEventSubscriber);
  }
}
