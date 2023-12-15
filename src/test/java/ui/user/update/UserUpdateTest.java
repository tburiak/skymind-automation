package ui.user.update;

import constants.TestTags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import skymind.automation.dto.UserDto;
import skymind.automation.pom.constants.UserRole;
import skymind.automation.pom.page.LoginPage;
import skymind.automation.pom.page.MainPage;
import ui.BaseTest;
import ui.data.UserUpdateTestData;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static skymind.automation.pom.constants.UserRole.*;
import static skymind.automation.util.Randomizer.generateRandomString;

@Tag(TestTags.END_TO_END_TEST)
@ExtendWith(UserUpdateTestData.class)
class UserUpdateTest extends BaseTest {

    private UserDto predefinedUser;

    @BeforeEach
    void createPreconditions() {
        predefinedUser = UserDto.builder()
                .firstName(generateRandomString("PredefinedFirst"))
                .lastName("PredefinedLast")
                .email(generateRandomString("test@gmail.com"))
                .roles(List.of("Basic Access"))
                .build();

        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickButtonAddUser()
                .typeFirstName(predefinedUser.getFirstName())
                .typeLastName(predefinedUser.getLastName())
                .typeEmailAddress(predefinedUser.getEmail())
                .clickButtonCreate()
                .verifyUserExists(predefinedUser)
                .changePage(MainPage.class)
                .logOut();

    }

    @Test
    @DisplayName("Update User Information")
    void updateUserInformationTest(UserDto userDto) {
        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickUserRow(predefinedUser)
                .clickButtonEditUserInformation()
                .typeFirstName(userDto.getFirstName())
                .typeLastName(userDto.getLastName())
                .clickIconSaveUserInformation()
                .verifyUserFirstNameExists(userDto.getFirstName())
                .verifyUserLastNameExists(userDto.getLastName());
    }

    @Test
    @DisplayName("Update Access Roles")
    void updateAccessRolesTest() {

        Set<UserRole> userRolesToAdd = EnumSet.of(ADMIN, INSIGHTS);
        Set<UserRole> userRolesToVerify = EnumSet.of(BASIC_ACCESS, ADMIN, INSIGHTS);

        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickUserRow(predefinedUser)
                .clickButtonEditRoles()
                .switchOnUserRoles(userRolesToVerify)
                .clickIconSaveRoles()
                .verifyUserRolesSwitchedOn(userRolesToAdd);
    }

    @Test
    @DisplayName("Remove All Access Roles Except 'Basic Access'")
    void removeAllAccessRoles() {

        Set<UserRole> userRolesToSwitchOff = EnumSet.allOf(UserRole.class);
        userRolesToSwitchOff.remove(BASIC_ACCESS);

        at(LoginPage.class)
                .loginToApplication()
                .clickWidgetMyCompany()
                .clickUsersTab()
                .clickUserRow(predefinedUser)
                .clickButtonEditRoles()
                .switchOffUserRoles(userRolesToSwitchOff)
                .clickIconSaveRoles()
                .verifyUserRolesSwitchedOff(userRolesToSwitchOff);
    }

}
