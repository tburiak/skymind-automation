package skymind.automation.pom.constants.xpaths;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginPageXPaths {
    public static final String INPUT_EMAIL_XPATH = "//*[@id=\"username\"]";
    public static final String INPUT_PASSWORD_XPATH = "//*[@id=\"password\"]";
    public static final String BUTTON_LOGIN_XPATH = "//*[@id=\"kc-login\"]";
}
