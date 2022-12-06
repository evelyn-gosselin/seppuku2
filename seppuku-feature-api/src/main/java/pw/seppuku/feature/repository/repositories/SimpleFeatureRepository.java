package pw.seppuku.feature.repository.repositories;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;

import java.util.*;

public final class SimpleFeatureRepository implements FeatureRepository {

    private final List<Feature> features;
    private final Set<UUID> presentUniqueIdentifiers;

    public SimpleFeatureRepository() {
        this(new ArrayList<>(), new HashSet<>());
    }

    public SimpleFeatureRepository(final List<Feature> features, final Set<UUID> presentUniqueIdentifiers) {
        this.features = features;
        this.presentUniqueIdentifiers = presentUniqueIdentifiers;
    }

    @Override
    public <T extends Feature> void add(final T featureToAdd) throws DuplicateUniqueIdentifierFeatureException {
        if (presentUniqueIdentifiers.contains(featureToAdd.uniqueIdentifier())) {
            throw new DuplicateUniqueIdentifierFeatureException(featureToAdd.uniqueIdentifier());
        }

        presentUniqueIdentifiers.add(featureToAdd.uniqueIdentifier());
        features.add(featureToAdd);
    }

    @Override
    public <T extends Feature> void remove(final T featureToRemove) {
        presentUniqueIdentifiers.remove(featureToRemove.uniqueIdentifier());
        features.remove(featureToRemove);
    }

    @Override
    public <T extends Feature> void addAll(final Collection<? extends T> featuresToAdd) throws DuplicateUniqueIdentifierFeatureException {
        for (final var featureToAdd : featuresToAdd) {
            add(featureToAdd);
        }
    }

    @Override
    public <T extends Feature> void removeAll(final Collection<? extends T> featuresToRemove) {
        for (final var featureToRemove : featuresToRemove) {
            remove(featureToRemove);
        }
    }
}
