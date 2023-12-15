package ui;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import skymind.automation.pom.page.LoginPage;

class LoginTest extends BaseTest {

    @Attachment(value = "Failed Test Case Screenshot", type = "image/png")
    protected byte[] captureScreenshotOnFailure() {
        return loginPage.captureScreenshot();
    }

    @Test
    @Tag("login")
    void testLogin() {
        createInstance(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickButtonAddUser()
                .typeFirstName()
                .typeLastName()
                .typeEmailAddress()
                .clickButtonCreate();
    }

}
