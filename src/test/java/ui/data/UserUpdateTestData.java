package ui.data;

import skymind.automation.dto.UserDto;
import skymind.automation.resolver.TestDataResolver;

import java.util.List;

import static skymind.automation.util.Randomizer.generateRandomString;

public class UserUpdateTestData extends TestDataResolver {

    private static UserDto updateUserInformationTest() {
        return UserDto.builder()
                .firstName(generateRandomString("First"))
                .lastName(generateRandomString("Last"))
                .roles(List.of("Basic Access"))
                .build();
    }

}
