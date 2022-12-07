package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.resolver.Inject;
import pw.seppuku.transform.bundle.TransformerBundle;

public final class StringToPersistentFeatureTransformer extends
    AbstractStringToFeatureTransformer<PersistentFeature> {

  @Inject
  public StringToPersistentFeatureTransformer(final FeatureRepository featureRepository,
      final TransformerBundle transformerBundle) {
    super(featureRepository, transformerBundle, PersistentFeature.class);
  }
}
