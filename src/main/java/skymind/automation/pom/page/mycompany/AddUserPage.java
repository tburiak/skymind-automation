package skymind.automation.pom.page.mycompany;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import io.qameta.allure.Step;
import skymind.automation.factory.BasePageFactory;
import skymind.automation.pom.constants.UserRole;
import skymind.automation.pom.page.BasePage;

import java.util.Set;
import java.util.function.Function;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddUserPage extends BasePage {

    private static final String SWITCHER_ROLE_SELECTOR = "//*[contains(@class,'switchBase')]";
    private static final String INPUT_ROLE_SELECTOR = "//input";

    private Locator inputUserFirstName;
    private Locator inputUserLastName;
    private Locator inputUserEmail;
    private Locator buttonCreate;
    private Locator tooltipErrorMessage;
    private Locator iconSaveUserInformation;
    private Locator iconSaveRoles;
    private Function<UserRole, String> toggleRoleSelector;

    @Override
    public void initElements() {
        inputUserFirstName = page.locator("//*[.='First Name']//input");
        inputUserLastName = page.locator("//*[.='Last Name']//input");
        inputUserEmail = page.locator("//*[.='E-Mail Address']//input");
        buttonCreate = page.locator("//button[.='CREATE']");
        tooltipErrorMessage = page.locator("//div[@role='tooltip']");
        iconSaveUserInformation = page.locator("//p[.='User Information']//following-sibling::div/button[2]");
        iconSaveRoles = page.locator("//p[.='Roles']//following-sibling::div/button[2]");
        toggleRoleSelector = role -> String.format("//div[text()='%s']//preceding-sibling::*", role.getRoleName());
    }

    /*
    action methods
    */

    @Step("Type user first name into 'First Name' field")
    public AddUserPage typeFirstName(String firstName) {
        inputUserFirstName.fill(firstName);
        return this;
    }

    @Step("Type user last name into 'Last Name' field")
    public AddUserPage typeLastName(String lastName) {
        inputUserLastName.fill(lastName);
        return this;
    }

    @Step("Type user email into 'E-Mail Address' field")
    public AddUserPage typeEmailAddress(String email) {
        inputUserEmail.fill(email);
        return this;
    }

    @Step("Click on button 'Create' user")
    public UsersPage clickButtonCreate() {
        buttonCreate.click();
        return BasePageFactory.createInstance(page, UsersPage.class);
    }

    @Step("Switch on user roles")
    public AddUserPage switchOnUserRoles(Set<UserRole> userRoles) {
        userRoles.forEach(role -> {
            Locator roleLocator = page.locator(toggleRoleSelector.apply(role));
            Locator switcherUserRole = roleLocator.locator(SWITCHER_ROLE_SELECTOR);
            Locator inputUserRole = roleLocator.locator(INPUT_ROLE_SELECTOR);
            if (!inputUserRole.isChecked()) {
                switcherUserRole.click();
            }
        });
        return this;
    }

    @Step("Switch on user roles")
    public AddUserPage switchOffUserRoles(Set<UserRole> userRoles) {
        userRoles.forEach(role -> {
            Locator roleLocator = page.locator(toggleRoleSelector.apply(role));
            Locator switcherUserRole = roleLocator.locator(SWITCHER_ROLE_SELECTOR);
            Locator inputUserRole = roleLocator.locator(INPUT_ROLE_SELECTOR);
            if (inputUserRole.isChecked()) {
                switcherUserRole.click();
            }
        });
        return this;
    }

    @Step("Click on icon 'Save User Information'")
    public UserDetailsPage clickIconSaveUserInformation() {
        iconSaveUserInformation.click();
        return BasePageFactory.createInstance(page, UserDetailsPage.class);
    }

    @Step("Click on icon 'Save User Roles'")
    public UserDetailsPage clickIconSaveRoles() {
        iconSaveRoles.click();
        return BasePageFactory.createInstance(page, UserDetailsPage.class);
    }

    /*
    validation methods
    */

    @Step("Verify button 'Create' state")
    public AddUserPage verifyButtonCreateEnabled(boolean isEnabled) {
        assertThat(buttonCreate).isEnabled(new LocatorAssertions.IsEnabledOptions().setEnabled(isEnabled));
        return this;
    }

    @Step("Verify error message is appeared")
    public AddUserPage verifyErrorMessageExists(String errorMessage) {
        assertThat(tooltipErrorMessage).isVisible();
        assertThat(tooltipErrorMessage).hasText(errorMessage);
        return this;
    }



}
