package pw.seppuku.feature.repository;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface FeatureRepository extends Iterable<Feature> {

    <T extends Feature> void add(final T featureToAdd) throws DuplicateUniqueIdentifierFeatureException;

    <T extends Feature> void remove(final T featureToRemove);

    <T extends Feature> void addAll(final Collection<? extends T> featuresToAdd) throws DuplicateUniqueIdentifierFeatureException;

    <T extends Feature> void removeAll(final Collection<? extends T> featuresToRemove);

    <T extends Feature> List<T> findFeaturesByClassAndPredicate(final Class<T> featureClass, final Predicate<T> predicate);

    <T extends Feature> List<T> findFeaturesByClass(final Class<T> featureClass);

    <T extends Feature> List<T> findFeaturesByHumanIdentifier(final String humanIdentifier, final Class<T> featureClass);

    <T extends Feature> T findFeatureByClass(final Class<T> featureClass) throws CouldNotBeFoundFeatureException;

    <T extends Feature> T findFeatureByUniqueIdentifier(final UUID uniqueIdentifier, final Class<T> featureClass) throws CouldNotBeFoundFeatureException;

    default Stream<Feature> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    default Stream<Feature> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }
}
