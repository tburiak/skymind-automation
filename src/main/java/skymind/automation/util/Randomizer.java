package skymind.automation.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Randomizer {

    public static String generateRandomString() {
        return String.valueOf(Instant.now().toEpochMilli());
    }

    public static String generateRandomString(String prefix) {
        return prefix + Randomizer.generateRandomString();
    }

}
