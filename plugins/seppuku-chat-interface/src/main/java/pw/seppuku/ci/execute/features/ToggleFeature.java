package pw.seppuku.ci.execute.features;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.feature.execute.ExecutableFeature;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.resolver.Inject;
import pw.seppuku.transform.bundle.TransformerBundle;
import pw.seppuku.transform.exception.TransformException;

public final class ToggleFeature extends ExecutableFeature {

  private static final UUID TOGGLE_UNIQUE_IDENTIFIER = UUID.fromString(
      "356e6b42-c3f8-4b73-986b-e3a5323fd57b");
  private static final String TOGGLE_HUMAN_IDENTIFIER = "toggle";
  private static final Version TOGGLE_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> TOGGLE_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  private final TransformerBundle transformerBundle;

  @Inject
  public ToggleFeature(final TransformerBundle transformerBundle) {
    super(TOGGLE_UNIQUE_IDENTIFIER, TOGGLE_HUMAN_IDENTIFIER, TOGGLE_VERSION, TOGGLE_AUTHORS);
    this.transformerBundle = transformerBundle;
  }

  @Override
  public void execute(final String... rest) throws TransformException {
    final var humanIdentifier = rest[0];
    final var toggleableFeature = transformerBundle.transform(String.class, ToggleableFeature.class,
        humanIdentifier);
    toggleableFeature.setRunning(!toggleableFeature.isRunning());
  }

  @Override
  public void load() {
  }

  @Override
  public void unload() {
  }
}
