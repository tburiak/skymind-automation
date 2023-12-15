package skymind.automation.util;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import skymind.automation.factory.BrowserFactory;

import java.util.Objects;

import static skymind.automation.config.TestConfigurationProvider.getConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BrowserManager {

    public static Browser getBrowser(final Playwright playwright) {
        Objects.requireNonNull(getConfig().browser(), "Please specify the browser type in your configuration." +
                " The 'browser()' method should not return null.");
        return BrowserFactory.valueOf(getConfig().browser().toUpperCase()).createInstance(playwright);
    }
}
