package ui.user.creation.data;

import skymind.automation.dto.UserDto;
import skymind.automation.resolver.TestDataResolver;

import java.util.List;

import static skymind.automation.util.Randomizer.generateRandomString;

public class UserCreationTestData extends TestDataResolver {

    private static UserDto validUserCreationTest() {
        return UserDto.builder()
                .firstName(generateRandomString("First"))
                .lastName(generateRandomString("Last1"))
                .email(generateRandomString("FirstLast1@test.com"))
                .roles(List.of("Basic Access"))
                .build();
    }

    private static UserDto duplicateEmailPreventionTest() {
        return UserDto.builder()
                .email("test_user@company11.com")
                .build();
    }

    private static UserDto missingUserInformationTest() {
        return UserDto.builder()
                .firstName(generateRandomString("First"))
                .email(generateRandomString("FirstLast1@test.com"))
                .build();
    }

}
