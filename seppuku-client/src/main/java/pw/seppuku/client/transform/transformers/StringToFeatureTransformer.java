package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.Feature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.resolver.Inject;
import pw.seppuku.transform.bundle.TransformerBundle;

public final class StringToFeatureTransformer extends AbstractStringToFeatureTransformer<Feature> {

  @Inject
  public StringToFeatureTransformer(final FeatureRepository featureRepository,
      final TransformerBundle transformerBundle) {
    super(featureRepository, transformerBundle, Feature.class);
  }
}
