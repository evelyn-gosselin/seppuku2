package pw.seppuku.access;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;

public final class SeppukuAccessPlugin extends AbstractPlugin {

  private static final UUID SEPPUKU_ACCESS_UNIQUE_IDENTIFIER = UUID.fromString(
      "a9ea3f49-9ef5-4777-aabb-127afd5b7f29");
  private final static String SEPPUKU_ACCESS_HUMAN_IDENTIFIER = "seppuku-access";
  private final static Version SEPPUKU_ACCESS_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_ACCESS_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  public SeppukuAccessPlugin() {
    super(SEPPUKU_ACCESS_UNIQUE_IDENTIFIER, SEPPUKU_ACCESS_HUMAN_IDENTIFIER, SEPPUKU_ACCESS_VERSION,
        SEPPUKU_ACCESS_AUTHORS);
  }

  @Override
  public void load() {
  }

  @Override
  public void unload() {
  }
}
