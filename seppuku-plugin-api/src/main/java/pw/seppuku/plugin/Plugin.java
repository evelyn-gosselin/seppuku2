package pw.seppuku.plugin;

import java.util.List;
import java.util.UUID;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

public interface Plugin {

  UUID uniqueIdentifier();

  String humanIdentifier();

  Version version();

  List<Author> authors();

  void load() throws Exception;

  void unload() throws Exception;
}
