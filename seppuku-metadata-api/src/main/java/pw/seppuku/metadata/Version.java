package pw.seppuku.metadata;

import java.util.Optional;

public record Version(int major, int minor, int patch, Optional<String> release,
                      Optional<Integer> build) {

  @Override
  public String toString() {
    return major + "." + minor + "." + patch + release.map(s -> "-" + s).orElse("") + build.map(
        n -> "+" + n).orElse("");
  }
}
