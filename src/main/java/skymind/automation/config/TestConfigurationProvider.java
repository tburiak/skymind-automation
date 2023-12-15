package skymind.automation.config;


import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigCache;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TestConfigurationProvider {

    public static TestConfiguration getConfig() {
        return ConfigCache.getOrCreate(TestConfiguration.class);
    }
}
