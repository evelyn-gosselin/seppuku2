package pw.seppuku.feature.repository;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.exception.exceptions.DuplicateUniqueIdentifierFeatureException;

import java.util.Collection;

public interface FeatureRepository {

    <T extends Feature> void add(final T featureToAdd) throws DuplicateUniqueIdentifierFeatureException;

    <T extends Feature> void remove(final T featureToRemove);

    <T extends Feature> void addAll(final Collection<? extends T> featuresToAdd) throws DuplicateUniqueIdentifierFeatureException;

    <T extends Feature> void removeAll(final Collection<? extends T> featuresToRemove);
}
