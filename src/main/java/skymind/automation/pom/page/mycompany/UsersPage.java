package skymind.automation.pom.page.mycompany;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;
import skymind.automation.factory.BasePageFactory;
import skymind.automation.dto.UserDto;
import skymind.automation.pom.page.BasePage;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class UsersPage extends BasePage {

    private Locator buttonAddUser;
    private Locator tableUsers;

    @Override
    public void initElements() {
        buttonAddUser = page.locator("//*[text()='Add User']");
        tableUsers = page.locator("//div[@id='content']//table[@role='table']");
    }

    /*
    action methods
    */

    @Step("Click on 'Add User' button")
    public AddUserPage clickButtonAddUser() {
        buttonAddUser.click();
        return BasePageFactory.createInstance(page, AddUserPage.class);
    }

    @Step("Click on user row")
    public UserDetailsPage clickUserRow(UserDto user) {
        String rowSelector = "tbody > tr";
        tableUsers.locator(rowSelector)
                .filter(new Locator.FilterOptions().setHasText(user.getUserAsString()))
                .click();
        return BasePageFactory.createInstance(page, UserDetailsPage.class);
    }

    /*
    validation methods
    */

    @Step("Verify new user exists")
    public UsersPage verifyUserExists(UserDto expectedUser) {
        String userAsString = expectedUser.getUserAsString();
        String rowSelector = "tbody > tr";
        tableUsers.locator(rowSelector)
                .filter(new Locator.FilterOptions().setHasText(userAsString))
                .waitFor(new Locator.WaitForOptions().setState(VISIBLE));
        return this;
    }

}
