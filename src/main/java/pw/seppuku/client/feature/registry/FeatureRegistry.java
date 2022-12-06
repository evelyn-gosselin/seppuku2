package pw.seppuku.client.feature.registry;

import pw.seppuku.client.feature.Feature;
import pw.seppuku.client.feature.exception.FeatureException;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface FeatureRegistry<T extends Feature> {

    <F extends T> boolean add(final F feature);

    <F extends T> boolean remove(final F feature);

    boolean addAll(final Collection<? extends T> features);

    boolean removeAll(final Collection<? extends T> features);

    boolean retainAll(final Collection<? extends T> features);

    <F extends T> List<F> findFeaturesByClassAndPredicate(final Class<F> featureClass, final Predicate<F> predicate) throws FeatureException;

    <F extends T> List<F> findFeaturesByClass(final Class<F> featureClass) throws FeatureException;

    <F extends T> F findFeatureByUniqueIdentifier(final UUID uniqueIdentifier, final Class<F> featureClass) throws FeatureException;

    <F extends T> List<F> findFeaturesByHumanIdentifier(final String humanIdentifier, final Class<F> featureClass) throws FeatureException;

    <F extends T> F findFeatureByHumanIdentifier(final String humanIdentifier, final Class<F> featureClass) throws FeatureException;

    <F extends T> List<F> findFeaturesByAuthor(final String author, final Class<F> featureClass) throws FeatureException;
}
