package skymind.automation.factory;

import com.microsoft.playwright.Page;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import skymind.automation.pom.page.BasePage;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BasePageFactory {

    public static <T extends BasePage> T createInstance(final Page page, final Class<T> clazz) {
        try {
            BasePage instance = clazz.getDeclaredConstructor().newInstance();
            instance.initPage(page);
            instance.initElements();
            return clazz.cast(instance);
        } catch (Exception e) {
            log.error("BasePageFactory::createInstance", e);
        }

        throw new IllegalArgumentException("Page class instantiation failed.");
    }
}
