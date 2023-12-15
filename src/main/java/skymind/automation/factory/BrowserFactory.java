package skymind.automation.factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;

import static skymind.automation.config.TestConfigurationProvider.getConfig;

public enum BrowserFactory {
    CHROMIUM {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.chromium().launch(options().setChannel("chrome"));
        }
    },
    FIREFOX {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.firefox().launch(options());
        }
    },
    WEBKIT {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.webkit().launch(options());
        }
    };

    public LaunchOptions options() {
        return new LaunchOptions()
                .setHeadless(getConfig().headless())
                .setSlowMo(getConfig().slowMotion());
    }

    public abstract Browser createInstance(final Playwright playwright);
}
