package API.auth;

import annotations.Records;
import obj.User;
import org.testng.annotations.Test;
import utils.api.ApiController;
import utils.auth.Auth;
import utils.baseTest.BaseTest_API;

import static org.testng.Assert.assertEquals;

public class TCA001_AuthViaApi extends BaseTest_API {
    private String token;
    private User userProfile;
    private String propEmail;
    private String propId;
    private String respEmail;
    private String respId;
    @Records(
            creator = "Eugene Dolbik",
            creationDate = "01/01/2024",
            manualTCLink = "",
            functionalityTag = "login"
    )

    public void completePreconditions(){
        LOGGER.info("Preconditions: get data from prop");
        propEmail = Auth.getCredByName("defaultUsername");
        propId = Auth.getCredByName("defaultUserId");
    }

    @Test
    public void TCA001_AuthViaApi(){
        LOGGER.info("Step #1: get auth token with for default user");
        token = ApiController.getAuthToken("defaultUsername", "defaultPass");

        LOGGER.info("Step #2: get user profile info for received token");
        userProfile = ApiController.getUserProfile(token);
        respId = userProfile.getId();
        respEmail = userProfile.getEmail();

        LOGGER.info("Step #2: validate received user profile info");
        assertEquals(propId, respId, "Received id is different from the expected");
        soft.assertEquals(propEmail, respEmail, "Received email is different from the expected");
        soft.assertAll();
    }

    public void completePostconditions(){
        LOGGER.info("CleanUp: logout");
        ApiController.logoutViaApi(token);
    }
}
