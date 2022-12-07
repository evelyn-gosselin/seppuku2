package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.resolver.Inject;

public final class StringToFeatureTransformer extends AbstractStringToFeatureTransformer<Feature> {

  @Inject
  public StringToFeatureTransformer(final FeatureRepository featureRepository) {
    super(featureRepository, Feature.class);
  }
}
