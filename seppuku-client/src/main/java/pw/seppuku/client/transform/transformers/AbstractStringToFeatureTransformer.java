package pw.seppuku.client.transform.transformers;

import java.util.UUID;
import pw.seppuku.feature.Feature;
import pw.seppuku.feature.exception.exceptions.CouldNotBeFoundFeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.transform.AbstractTransformer;
import pw.seppuku.transform.bundle.TransformerBundle;
import pw.seppuku.transform.exception.TransformException;
import pw.seppuku.transform.exception.exceptions.TransformLazyException;

public abstract class AbstractStringToFeatureTransformer<T extends Feature> extends
    AbstractTransformer<String, T> {

  private final FeatureRepository featureRepository;
  private final TransformerBundle transformerBundle;

  private final Class<T> featureType;
  private final String featureTypeName;

  protected AbstractStringToFeatureTransformer(final FeatureRepository featureRepository,
      final TransformerBundle transformerBundle, final Class<T> featureType) {
    super(String.class, featureType);
    this.featureRepository = featureRepository;
    this.transformerBundle = transformerBundle;

    this.featureType = featureType;
    this.featureTypeName = featureType.getTypeName();
  }

  @Override
  public T transform(final String fromInstance) throws TransformLazyException {
    try {
      final var uuid = transformerBundle.transform(String.class, UUID.class, fromInstance);
      return featureRepository.findFeatureByUniqueIdentifier(uuid, featureType);
    } catch (TransformException | CouldNotBeFoundFeatureException exception) {
      return featureRepository.findFeaturesByHumanIdentifier(fromInstance, featureType).stream()
          .findFirst().orElseThrow(() -> new TransformLazyException(
              "Could not find " + featureTypeName + " '" + fromInstance + "'"));
    }
  }
}
