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

public class UserDetailsPage extends BasePage {

    private static final String INPUT_ROLE_SELECTOR = "//input";
    private Locator buttonEditUserInformation;
    private Locator buttonEditRoles;
    private Locator labelUserFirstName;
    private Locator labelUserLastName;
    private Function<UserRole, String> toggleRoleSelector;

    @Override
    public void initElements() {
        buttonEditUserInformation = page.locator("//p[.='User Information']//following-sibling::div//button");
        buttonEditRoles = page.locator("//p[.='Roles']//following-sibling::div//button");
        labelUserFirstName = page.locator("//*[.='First Name']//following-sibling::div");
        labelUserLastName = page.locator("//*[.='Last Name']//following-sibling::div");
        toggleRoleSelector = role -> String.format("//div[text()='%s']//preceding-sibling::*", role.getRoleName());
    }

    /*
    action methods
    */

    @Step("Click on button 'Edit User Information'")
    public AddUserPage clickButtonEditUserInformation() {
        buttonEditUserInformation.click();
        return BasePageFactory.createInstance(page, AddUserPage.class);
    }

    @Step("Click on button 'Edit User Roles'")
    public AddUserPage clickButtonEditRoles() {
        buttonEditRoles.click();
        return BasePageFactory.createInstance(page, AddUserPage.class);
    }

    /*
    validation methods
    */

    @Step("Verify 'First Name' input field value")
    public UserDetailsPage verifyUserFirstNameExists(String firstName) {
        assertThat(labelUserFirstName).isVisible();
        assertThat(labelUserFirstName).hasText(firstName);
        return this;
    }

    @Step("Verify 'Last Name' input field value")
    public UserDetailsPage verifyUserLastNameExists(String lastName) {
        assertThat(labelUserLastName).isVisible();
        assertThat(labelUserLastName).hasText(lastName);
        return this;
    }

    @Step("Verify user roles are switched on")
    public UserDetailsPage verifyUserRolesSwitchedOn(Set<UserRole> userRoles) {
        userRoles.forEach(role -> {
            Locator roleLocator = page.locator(toggleRoleSelector.apply(role));
            Locator inputUserRole = roleLocator.locator(INPUT_ROLE_SELECTOR);
            assertThat(inputUserRole).isDisabled();
            assertThat(inputUserRole).isChecked();
        });
        return this;
    }

    @Step("Verify user roles are switched off")
    public UserDetailsPage verifyUserRolesSwitchedOff(Set<UserRole> userRoles) {
        userRoles.forEach(role -> {
            Locator roleLocator = page.locator(toggleRoleSelector.apply(role));
            Locator inputUserRole = roleLocator.locator(INPUT_ROLE_SELECTOR);
            assertThat(inputUserRole).isDisabled();
            assertThat(inputUserRole).isChecked(new LocatorAssertions.IsCheckedOptions().setChecked(false));
        });
        return this;
    }
}
