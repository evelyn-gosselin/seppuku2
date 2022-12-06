package pw.seppuku.metadata;

import java.util.Optional;

public record Author(String alias, Optional<String> firstName, Optional<String> lastName, Optional<String> email) {

    @Override
    public String toString() {
        return alias +
                firstName.map(s -> " " + s).orElse("") +
                lastName.map(s -> " " + s).orElse("") +
                email.map(s -> " <" + s + ">");
    }
}
