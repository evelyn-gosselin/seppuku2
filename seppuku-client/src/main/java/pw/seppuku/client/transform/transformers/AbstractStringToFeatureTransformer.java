package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public abstract class AbstractStringToFeatureTransformer<T extends Feature> extends
    AbstractTransformer<String, T> {

  private final FeatureRepository featureRepository;
  private final Class<T> featureType;
  private final String featureTypeName;

  protected AbstractStringToFeatureTransformer(final FeatureRepository featureRepository,
      final Class<T> featureType) {
    super(String.class, featureType);
    this.featureRepository = featureRepository;
    this.featureType = featureType;
    this.featureTypeName = featureType.getTypeName();
  }

  @Override
  public T transform(final String fromInstance) throws TransformLazyException {
    return featureRepository.findFeaturesByHumanIdentifier(fromInstance, featureType).stream()
        .findFirst().orElseThrow(() -> new TransformLazyException(
            "Could not find " + featureTypeName + " '" + fromInstance + "'"));
  }
}
