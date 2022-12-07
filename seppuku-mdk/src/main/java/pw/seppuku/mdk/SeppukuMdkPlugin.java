package pw.seppuku.mdk;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;

public final class SeppukuMdkPlugin extends AbstractPlugin {

  private static final UUID SEPPUKU_MDK_UNIQUE_IDENTIFIER = UUID.fromString(
      "edbd89bd-8d95-4fa2-a1cc-2c185ee27581");
  private final static String SEPPUKU_MDK_HUMAN_IDENTIFIER = "seppuku-mdk";
  private final static Version SEPPUKU_MDK_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_MDK_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  public SeppukuMdkPlugin() {
    super(SEPPUKU_MDK_UNIQUE_IDENTIFIER, SEPPUKU_MDK_HUMAN_IDENTIFIER, SEPPUKU_MDK_VERSION,
        SEPPUKU_MDK_AUTHORS);
  }

  @Override
  public void load() {
  }

  @Override
  public void unload() {
  }
}
