package skymind.automation.pom.page;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import skymind.automation.factory.BasePageFactory;

import static skymind.automation.config.TestConfigurationProvider.getConfig;

public abstract class BasePage {

    protected Page page;

    public void initPage(final Page page) {
        this.page = page;
        page.setDefaultTimeout(getConfig().timeout());
    }

    public abstract void initElements();

    public <T extends BasePage> T changePage(Class<T> basePage) {
        return BasePageFactory.createInstance(page, basePage);
    }

    @Step
    public byte[] captureScreenshot() {
        return page.screenshot();
    }
}