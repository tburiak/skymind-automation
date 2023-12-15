package ui.user.creation;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import skymind.automation.dto.UserDto;
import skymind.automation.pom.page.LoginPage;
import ui.BaseTest;
import ui.user.creation.data.UserCreationTestData;
import ui.user.creation.data.UserUpdateTestData;

import java.util.List;

import static skymind.automation.util.Randomizer.generateRandomString;

@ExtendWith(UserUpdateTestData.class)
class UserUpdateTest extends BaseTest {

    @Attachment(value = "Failed Test Case Screenshot", type = "image/png")
    protected byte[] captureScreenshotOnFailure() {
        return loginPage.captureScreenshot();
    }

    UserDto predefinedUser = UserDto.builder()
            .firstName(generateRandomString("PredifinedFirst"))
            .lastName(generateRandomString("PredifineLast1"))
            .roles(List.of("Basic Access"))
            .build();


    @BeforeAll
    void createPreconditions() {
        createContextAndPage();
        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickButtonAddUser()
                .typeFirstName(predefinedUser.getFirstName())
                .typeLastName(predefinedUser.getLastName())
                .clickButtonCreate()
                .verifyUserExists(predefinedUser);
    }

    @Test
    @Tag("user")
    @DisplayName("Update User Information")
    void updateUserInformation(UserDto userDto) {
        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickUserRow(predefinedUser)
                .clickButtonEditUserInformation()
                .typeFirstName(userDto.getFirstName())
                .typeLastName(userDto.getLastName())
                .clickIconSave()
                .verifyUserFirstNameExists(userDto.getFirstName())
                .verifyUserLastNameExists(userDto.getLastName());
    }

}
