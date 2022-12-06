package pw.seppuku.plugin;

import pw.seppuku.event.bus.EventBus;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;

import java.util.List;
import java.util.UUID;

public interface Plugin {

    UUID uniqueIdentifier();

    String humanIdentifier();

    Version version();

    List<Author> authors();

    void load(final EventBus eventBus, final FeatureRepository featureRepository) throws DuplicateUniqueIdentifierFeatureException;

    void unload(final EventBus eventBus, final FeatureRepository featureRepository);
}
