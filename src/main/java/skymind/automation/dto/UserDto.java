package skymind.automation.model;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;


@Data
@Builder(toBuilder = true)
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

    public String getFullName() {
        return StringUtils.joinWith(SPACE, firstName, lastName);
    }

    public String getRolesAsString() {
        return String.join(SPACE, roles);
    }

    public String getUserAsString() {
        return StringUtils.joinWith(SPACE, firstName, lastName) + email + String.join(EMPTY, roles);
    }
}
