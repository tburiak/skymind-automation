package ui;

import com.google.common.collect.ImmutableMap;
import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;
import skymind.automation.factory.BasePageFactory;
import skymind.automation.pom.page.BasePage;
import skymind.automation.util.BrowserManager;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static skymind.automation.config.TestConfigurationProvider.getConfig;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    private String testMethodName;


    @RegisterExtension
    BeforeTestExecutionCallback callbackBefore = context -> testMethodName = context.getTestMethod().get().getName();

    @RegisterExtension
    AfterTestExecutionCallback callbackAfter = testContext -> {
        Optional<Throwable> exception = testContext.getExecutionException();
        if (exception.isPresent()) {
            String traceFilePath = stopTracing();
            File traceFile = new File(traceFilePath);
            Allure.attachment("tracing", FileUtils.openInputStream(traceFile));
        }
    };

    protected <T extends BasePage> T at(Class<T> basePage) {
        return BasePageFactory.createInstance(page, basePage);
    }

    @BeforeAll
    public void createPlaywrightAndBrowserInstancesAndSetupAllureEnvironment() {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", System.getProperty("os.name"))
                        .put("Version", System.getProperty("os.version"))
                        .put("Browser", StringUtils.capitalize(getConfig().browser()))
                        .put("Context URL", getConfig().baseUrl())
                        .build(),
                getConfig().allureResultsDir() + "/");
    }

    @AfterAll
    public void tearDown() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    protected void createContextAndPage() {
        String videosPath = getConfig().baseArtifactPath() + testMethodName;
        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get(videosPath))
                .setRecordVideoSize(640, 480));
        startTracing();
        page = context.newPage();
    }

    @AfterEach
    protected void closeContext() {
        context.close();
    }

    private void startTracing() {
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    private String stopTracing() {
        String tracePath = getConfig().baseArtifactPath() + testMethodName + "/tracing.zip";
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(tracePath)));
        return tracePath;
    }
}
