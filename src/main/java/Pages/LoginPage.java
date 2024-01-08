package Pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    private static By loginBtnLocator = By.xpath("//div[contains(@class, 'login_wrapper')]//input[@value = 'Login']");
    private static By usernameFieldLocator = By.xpath("//form//input[@placeholder = 'Username']");
    private static By passwordFieldLocator = By.xpath("//form//input[@placeholder = 'Password']");


    public LoginPage openPage() {
        BasePage.open();
        return this;
    }

    public static void login(String userName, String password){
        fillField(userName, usernameFieldLocator);
        fillField(password, passwordFieldLocator);
        clickOnElement(loginBtnLocator);
    }
}
