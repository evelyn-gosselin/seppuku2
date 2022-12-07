package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.resolver.Inject;

public final class StringToToggleableFeatureTransformer extends
    AbstractStringToFeatureTransformer<ToggleableFeature> {

  @Inject
  public StringToToggleableFeatureTransformer(final FeatureRepository featureRepository) {
    super(featureRepository, ToggleableFeature.class);
  }
}
