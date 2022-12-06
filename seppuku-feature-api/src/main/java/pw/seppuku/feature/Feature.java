package pw.seppuku.feature;

import java.util.List;
import java.util.UUID;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

public interface Feature {

  UUID uniqueIdentifier();

  String humanIdentifier();

  Version version();

  List<Author> authors();

  void load();

  void unload();
}
