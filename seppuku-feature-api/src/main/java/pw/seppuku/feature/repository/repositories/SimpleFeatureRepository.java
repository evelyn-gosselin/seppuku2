package pw.seppuku.feature.repository.repositories;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;

import java.util.*;
import java.util.function.Predicate;

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

    @Override
    public <T extends Feature> List<T> findFeaturesByClassAndPredicate(final Class<T> featureClass, final Predicate<T> predicate) {
        return stream()
                .filter(featureClass::isInstance)
                .map(featureClass::cast)
                .filter(predicate)
                .toList();
    }

    @Override
    public <T extends Feature> List<T> findFeaturesByClass(final Class<T> featureClass) {
        return findFeaturesByClassAndPredicate(featureClass, f -> true);
    }

    @Override
    public <T extends Feature> T findFeatureByUniqueIdentifier(final UUID uniqueIdentifier, final Class<T> featureClass) throws CouldNotBeFoundFeatureException {
        return findFeaturesByClassAndPredicate(featureClass, f -> f.uniqueIdentifier().equals(uniqueIdentifier)).stream()
                .findFirst()
                .orElseThrow(() -> new CouldNotBeFoundFeatureException(uniqueIdentifier.toString()));
    }

    @Override
    public Iterator<Feature> iterator() {
        return features.iterator();
    }
}
