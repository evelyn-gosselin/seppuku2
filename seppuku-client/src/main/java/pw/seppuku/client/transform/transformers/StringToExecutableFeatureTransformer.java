package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.execute.ExecutableFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.resolver.Inject;

public final class StringToExecutableFeatureTransformer extends
    AbstractStringToFeatureTransformer<ExecutableFeature> {

  @Inject
  public StringToExecutableFeatureTransformer(final FeatureRepository featureRepository) {
    super(featureRepository, ExecutableFeature.class);
  }
}
