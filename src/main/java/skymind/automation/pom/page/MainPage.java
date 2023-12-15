package skymind.automation.pom.page;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;
import skymind.automation.factory.BasePageFactory;
import skymind.automation.pom.page.mycompany.MyCompanyPage;

import static skymind.automation.pom.constants.MainWidget.MY_COMPANY;

public class MainPage extends BasePage {

    private Locator mainWidget;
    private Locator iconUserProfile;
    private Locator buttonLogOut;

    @Override
    public void initElements() {
        mainWidget = page.locator("h3");
        iconUserProfile = page.locator("//*[@data-testid='ArrowDropDownIcon']");
        buttonLogOut = page.locator("//span[.='Log out']");
    }

    /*
    action methods
    */

    @Step("Click on 'My Company' widget")
    public MyCompanyPage clickWidgetMyCompany() {
        mainWidget.filter().getByText(MY_COMPANY.getWidgetName()).click();
        return BasePageFactory.createInstance(page, MyCompanyPage.class);
    }

    @Step("Log out application")
    public LoginPage logOut() {
        iconUserProfile.click();
        buttonLogOut.click();
        return BasePageFactory.createInstance(page, LoginPage.class);
    }

}
