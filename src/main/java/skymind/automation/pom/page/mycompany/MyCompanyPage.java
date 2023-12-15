package skymind.automation.pom.page.mycompany;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;
import skymind.automation.factory.BasePageFactory;
import skymind.automation.pom.page.BasePage;

public class MyCompanyPage extends BasePage {

    private Locator subTabUsers;

    @Override
    public void initElements() {
        subTabUsers = page.locator("//a[@href='/my-company/users']/div");
    }

    /*
    action methods
    */

    @Step("Click on the 'My Company -> Users' tab")
    public UsersPage clickUsersTab() {
        subTabUsers.click();
        return BasePageFactory.createInstance(page, UsersPage.class);
    }
}
