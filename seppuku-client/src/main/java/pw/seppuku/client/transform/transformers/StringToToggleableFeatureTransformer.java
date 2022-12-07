package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.toggleable.ToggleableFeature;
import pw.seppuku.resolver.Inject;
import pw.seppuku.transform.bundle.TransformerBundle;

public final class StringToToggleableFeatureTransformer extends
    AbstractStringToFeatureTransformer<ToggleableFeature> {

  @Inject
  public StringToToggleableFeatureTransformer(final FeatureRepository featureRepository,
      final TransformerBundle transformerBundle) {
    super(featureRepository, transformerBundle, ToggleableFeature.class);
  }
}
