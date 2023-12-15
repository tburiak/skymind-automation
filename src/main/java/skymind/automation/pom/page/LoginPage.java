package skymind.automation.pom.page;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;
import skymind.automation.factory.BasePageFactory;

import static skymind.automation.config.TestConfigurationProvider.getConfig;
import static skymind.automation.pom.constants.xpaths.LoginPageXPaths.*;

public class LoginPage extends BasePage {

    private Locator inputEmail;
    private Locator inputPassword;
    private Locator buttonLogin;

    @Override
    public void initElements() {
        inputEmail = page.locator(INPUT_EMAIL_XPATH);
        inputPassword = page.locator(INPUT_PASSWORD_XPATH);
        buttonLogin = page.locator(BUTTON_LOGIN_XPATH);
    }

    /*
    action methods
    */

    @Step("Navigate to the Login page")
    public LoginPage open() {
        page.navigate(getConfig().baseUrl());
        return this;
    }

    @Step("Type email name into 'E-Mail Address' field")
    public LoginPage typeEmail(String email) {
        inputEmail.fill(email);
        return this;
    }

    @Step("Type password into 'Password' field")
    public LoginPage typePassword(String password) {
        inputPassword.fill(password);
        return this;
    }

    @Step("Click on the 'Login' button")
    public MainPage clickButtonLogin() {
        buttonLogin.click();
        return BasePageFactory.createInstance(page, MainPage.class);
    }

    @Step("Login to application")
    public MainPage loginToApplication() {
        open();
        typeEmail(getConfig().userName());
        typePassword(getConfig().userPassword());
        clickButtonLogin();
        return BasePageFactory.createInstance(page, MainPage.class);
    }

}
