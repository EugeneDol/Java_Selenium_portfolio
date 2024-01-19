package Pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    private static By loginBtnLocator = By.xpath("//form//button[@id = 'submit']");
    private static By usernameFieldLocator = By.xpath("//form//input[@id = 'email']");
    private static By passwordFieldLocator = By.xpath("//form//input[@id = 'password']");


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
