package ui.user.creation;

import constants.TestTags;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import skymind.automation.dto.UserDto;
import skymind.automation.pom.constants.MessageText;
import skymind.automation.pom.page.LoginPage;
import ui.BaseTest;
import ui.data.UserCreationTestData;

@Tag(TestTags.END_TO_END_TEST)
@ExtendWith(UserCreationTestData.class)
class UserCreationTest extends BaseTest {

    @Test
    @DisplayName("Valid User Creation")
    void validUserCreationTest(UserDto userDto) {
        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickButtonAddUser()
                .typeFirstName(userDto.getFirstName())
                .typeLastName(userDto.getLastName())
                .typeEmailAddress(userDto.getEmail())
                .clickButtonCreate()
                .verifyUserExists(userDto);
    }

    @Test
    @DisplayName("Duplicate Email Prevention")
    void duplicateEmailPreventionTest(UserDto userDto) {
        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickButtonAddUser()
                .typeEmailAddress(userDto.getEmail())
                .verifyButtonCreateEnabled(false)
                .verifyErrorMessageExists(MessageText.ADDRESS_ALREADY_EXISTS);
    }

    @Test
    @DisplayName("Missing User Information")
    void missingUserInformationTest(UserDto userDto) {
        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickButtonAddUser()
                .typeFirstName(userDto.getFirstName())
                .typeEmailAddress(userDto.getEmail())
                .verifyButtonCreateEnabled(false);
    }

    @Test
    @DisplayName("Failed test cases to check tracing")
    void failedTest() {
        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickButtonAddUser()
                .typeFirstName("fail")
                .verifyButtonCreateEnabled(true);
    }

}
