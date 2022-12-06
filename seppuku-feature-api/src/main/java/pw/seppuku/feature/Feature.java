package pw.seppuku.feature;

import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.UUID;

public interface Feature {

    UUID uniqueIdentifier();

    String humanIdentifier();

    Version version();

    List<Author> authors();
}

