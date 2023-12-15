package skymind.automation.pom.page;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;
import skymind.automation.factory.BasePageFactory;

public class MyCompanyPage extends BasePage {

    private Locator subTabUsers;

    @Override
    public void initElements() {
        subTabUsers = page.locator("//a[@href='/my-company/users']");
    }

    @Step("Click on the 'My Company -> Users' tab")
    public UsersPage clickUsersTab() {
        subTabUsers.click();
        return BasePageFactory.createInstance(page, UsersPage.class);
    }
}
