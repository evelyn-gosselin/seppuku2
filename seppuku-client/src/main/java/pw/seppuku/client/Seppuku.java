package pw.seppuku.client;

import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.feature.repository.repos.SimpleFeatureRepository;

public final class Seppuku {
    private static volatile Seppuku instance = null;

    private final FeatureRepository featureRepository = new SimpleFeatureRepository();

    private Seppuku() {}

    public FeatureRepository featureRepository() {
        return featureRepository;
    }

    public static Seppuku instance() {
        if (instance == null) {
            synchronized (Seppuku.class) {
                if (instance == null) {
                    instance = new Seppuku();
                }
            }
        }

        return instance;
    }
}
