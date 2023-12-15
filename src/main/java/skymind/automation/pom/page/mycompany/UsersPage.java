package skymind.automation.pom.page;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;
import skymind.automation.util.Randomizer;

public class UsersPage extends BasePage {

    private Locator buttonAddUser;
    private Locator inputUserFirstName;
    private Locator inputUserLastName;
    private Locator inputUserEmail;
    private Locator buttonCreate;

    @Override
    public void initElements() {
        buttonAddUser = page.locator("Add User");
        inputUserFirstName = page.locator("//*[.='First Name']//input");
        inputUserLastName = page.locator("//*[.='Last Name']//input");
        inputUserEmail = page.locator("//*[.='E-Mail Address']//input");
        buttonCreate = page.locator("//button[.='CREATE']");
    }

    @Step("Type user first name into 'First Name' field")
    public UsersPage typeFirstName(String firstName) {
        inputUserFirstName.fill(firstName);
        return this;
    }

    @Step("Type user first name into 'First Name' field")
    public UsersPage typeFirstName() {
        String randomFirstName = Randomizer.generateRandomString("TestFirstName");
        typeFirstName(randomFirstName);
        return this;
    }

    @Step("Type user last name into 'Last Name' field")
    public UsersPage typeLastName(String lastName) {
        inputUserLastName.fill(lastName);
        return this;
    }

    @Step("Type user last name into 'Last Name' field")
    public UsersPage typeLastName() {
        String randomLastName = Randomizer.generateRandomString("TestLastName");
        typeLastName(randomLastName);
        return this;
    }

    @Step("Type user email into 'E-Mail Address' field")
    public UsersPage typeEmailAddress(String email) {
        inputUserEmail.fill(email);
        return this;
    }

    @Step("Type user email into 'E-Mail Address' field")
    public UsersPage typeEmailAddress() {
        String randomEmail = Randomizer.generateRandomString("testEmail");
        typeEmailAddress(randomEmail);
        return this;
    }

    @Step("Click on button 'Create' user")
    public UsersPage clickButtonCreate() {
        buttonCreate.click();
        return this;
    }




}
