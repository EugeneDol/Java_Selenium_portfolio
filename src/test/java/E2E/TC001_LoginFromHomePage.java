package E2E;

import Pages.LoginPage;
import annotations.Records;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import utils.auth.Auth;
import utils.baseTest.BaseTest_E2E;

import java.net.MalformedURLException;
import java.net.URL;

public class TC001_LoginFromHomePage extends BaseTest_E2E {
    @Records(
            creator = "Eugene Dolbik",
            creationDate = "01/01/2024",
            manualTCLink = "",
            functionalityTag = "login"
    )

    @Test
    public void TC001_LoginFromHomePage() throws MalformedURLException {
        FirefoxOptions opt = new FirefoxOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://13.51.175.59:4444/"), opt);
        driver.get("https://google.com");
        driver.findElements(By.name("q"));
        System.out.println(driver.getTitle());
        driver.quit();
        /*String username = Auth.getDefaultUsername();
        String password = Auth.getDefaultPassword();

        LoginPage loginPage = new LoginPage();
        loginPage.openPage();
        loginPage.login(username, password);*/
    }
}
