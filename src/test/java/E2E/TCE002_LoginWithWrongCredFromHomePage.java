package E2E;

import Pages.LoginPage;
import annotations.Records;
import org.testng.annotations.Test;
import utils.auth.Auth;
import utils.baseTest.BaseTest_E2E;

public class TCE002_LoginWithWrongCredFromHomePage extends BaseTest_E2E {
    @Records(
            creator = "Eugene Dolbik",
            creationDate = "01/01/2024",
            manualTCLink = "",
            functionalityTag = "login"
    )

    @Test
    public void TCE002_LoginWithWrongCredFromHomePage(){
        String defaultUsername = Auth.getDefaultUsername();
        String usernameWrng = Auth.getCredByName("defaultUsernameWrong");
        String defaultPassword = Auth.getDefaultPassword();
        String passwordWrng = Auth.getCredByName("defaultPassWrong");

        LoginPage loginPage = new LoginPage();

        loginPage.openPage();
        loginPage.login(defaultUsername, passwordWrng);
        loginPage.login(usernameWrng, defaultPassword);
        loginPage.login(usernameWrng, passwordWrng);
        // TODO: 1/29/2024 - add validations
    }
}
