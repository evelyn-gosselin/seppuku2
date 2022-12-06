package pw.seppuku.client.feature.registry.registries;

import pw.seppuku.client.feature.Feature;
import pw.seppuku.client.feature.exception.FeatureException;
import pw.seppuku.client.feature.registry.FeatureRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public final class SimpleFeatureRegistry<T extends Feature> implements FeatureRegistry<T> {

    private final List<T> features;

    public SimpleFeatureRegistry() {
        this(new ArrayList<>());
    }

    public SimpleFeatureRegistry(final List<T> features) {
        this.features = features;
    }

    @Override
    public <F extends T> boolean add(final F feature) {
        return features.add(feature);
    }

    @Override
    public <F extends T> boolean remove(final F feature) {
        return features.remove(feature);
    }

    @Override
    public boolean addAll(Collection<? extends T> features) {
        return this.features.addAll(features);
    }

    @Override
    public boolean removeAll(Collection<? extends T> features) {
        return this.features.removeAll(features);
    }

    @Override
    public boolean retainAll(Collection<? extends T> features) {
        return this.features.retainAll(features);
    }

    @Override
    public <F extends T> List<F> findFeaturesByClassAndPredicate(final Class<F> featureClass, final Predicate<F> predicate) throws FeatureException {
        final var filteredFeatures = features.stream()
                .filter(feature -> featureClass.isAssignableFrom(feature.getClass()))
                .map(featureClass::cast)
                .filter(predicate)
                .toList();

        if (filteredFeatures.isEmpty()) {
            throw new FeatureException();
        }

        return filteredFeatures;
    }

    @Override
    public <F extends T> List<F> findFeaturesByClass(final Class<F> featureClass) throws FeatureException {
        return findFeaturesByClassAndPredicate(featureClass, f -> true);
    }

    @Override
    public <F extends T> F findFeatureByUniqueIdentifier(final UUID uniqueIdentifier, final Class<F> featureClass) throws FeatureException {
        return findFeaturesByClassAndPredicate(featureClass, f -> f.uniqueIdentifier().equals(uniqueIdentifier)).stream()
                .findFirst()
                .orElseThrow(FeatureException::new);
    }

    @Override
    public <F extends T> List<F> findFeaturesByHumanIdentifier(final String humanIdentifier, final Class<F> featureClass) throws FeatureException {
        return findFeaturesByClassAndPredicate(featureClass, f -> f.humanIdentifier().equals(humanIdentifier));
    }

    @Override
    public <F extends T> F findFeatureByHumanIdentifier(final String humanIdentifier, final Class<F> featureClass) throws FeatureException {
        return findFeaturesByHumanIdentifier(humanIdentifier, featureClass).stream()
                .findFirst()
                .orElseThrow(FeatureException::new);
    }

    @Override
    public <F extends T> List<F> findFeaturesByAuthor(final String author, final Class<F> featureClass) throws FeatureException {
        return findFeaturesByClassAndPredicate(featureClass, f -> f.author().equals(author));
    }
}
