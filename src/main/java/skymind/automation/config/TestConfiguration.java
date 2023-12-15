package skymind.automation.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:test.properties",
        "classpath:allure.properties"
})
public interface TestConfiguration extends Config {

    boolean headless();

    int timeout();

    String browser();

    @Key("base.url")
    String baseUrl();

    @Key("slow.motion")
    int slowMotion();

    @Key("base.artifact.path")
    String baseArtifactPath();

    @Key("allure.results.directory")
    String allureResultsDir();

    @Key("ui.user.name")
    String userName();

    @Key("ui.user.password")
    String userPassword();
}
