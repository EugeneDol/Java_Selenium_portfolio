package API.auth;

import annotations.Records;
import org.testng.annotations.Test;
import utils.api.ApiController;
import utils.auth.Auth;
import utils.baseTest.BaseTest_API;

import static org.testng.Assert.assertTrue;

public class TCA001_AuthViaApi extends BaseTest_API {
    private String token;
    private String profileEmail;
    private String propEmail;
    @Records(
            creator = "Eugene Dolbik",
            creationDate = "01/01/2024",
            manualTCLink = "",
            functionalityTag = "login"
    )

    @Test
    public void TCA001_AuthViaApi(){
        token = ApiController.getAuthToken("defaultUsername", "defaultPass");
        profileEmail = ApiController.getUserProfile(token);
        propEmail = Auth.getCredByName("defaultUsername");
        assertTrue(profileEmail.equals(propEmail), "Emails match");
    }
}
