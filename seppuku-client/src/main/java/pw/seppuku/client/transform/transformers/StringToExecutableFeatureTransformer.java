package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.execute.ExecutableFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.resolver.Inject;
import pw.seppuku.transform.bundle.TransformerBundle;

public final class StringToExecutableFeatureTransformer extends
    AbstractStringToFeatureTransformer<ExecutableFeature> {

  @Inject
  public StringToExecutableFeatureTransformer(final FeatureRepository featureRepository,
      final TransformerBundle transformerBundle) {
    super(featureRepository, transformerBundle, ExecutableFeature.class);
  }
}
