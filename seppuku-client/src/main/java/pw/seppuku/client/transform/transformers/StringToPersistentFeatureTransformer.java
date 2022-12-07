package pw.seppuku.client.transform.transformers;

import pw.seppuku.feature.persistent.PersistentFeature;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.resolver.Inject;

public final class StringToPersistentFeatureTransformer extends
    AbstractStringToFeatureTransformer<PersistentFeature> {

  @Inject
  public StringToPersistentFeatureTransformer(final FeatureRepository featureRepository) {
    super(featureRepository, PersistentFeature.class);
  }
}
