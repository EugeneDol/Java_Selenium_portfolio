package E2E;

import Pages.LoginPage;
import annotations.Records;
import org.testng.annotations.Test;
import utils.auth.Auth;
import utils.baseTest.BaseTest_E2E;

public class TCE001_LoginFromHomePage extends BaseTest_E2E {
    @Records(
            creator = "Eugene Dolbik",
            creationDate = "01/01/2024",
            manualTCLink = "",
            functionalityTag = "login"
    )

    @Test
    public void TCE001_LoginFromHomePage(){
        String username = Auth.getDefaultUsername();
        String password = Auth.getDefaultPassword();

        LoginPage loginPage = new LoginPage();
        loginPage.openPage();
        loginPage.login(username, password);
        // TODO: 1/29/2024 - add validations
    }
}
