package API.userProcessing;

import annotations.Records;
import obj.User;
import org.testng.annotations.Test;
import utils.api.ApiController;
import utils.auth.Auth;
import utils.baseTest.BaseTest_API;
import utils.reports.AllureHandler;

import static org.testng.Assert.assertEquals;

public class TCA002_AddUserViaApi extends BaseTest_API {
    private User newUser;
    private String newUserEmail;
    private String newUserName;
    private String propNewUserEmail;
    private String propNewUserName;

    @Records(
            creator = "Eugene Dolbik",
            creationDate = "21/01/2024",
            manualTCLink = "",
            functionalityTag = "registration"
    )

    public void completePreconditions(){
        LOGGER.info("Preconditions: get data from prop");
        propNewUserEmail = Auth.getCredByName("newUserEmail");
        propNewUserName = Auth.getCredByName("newUserName");

    }

    @Test
    public void TCA002_AddUserViaApi(){
        LOGGER.info("Step #1: create new user");
        newUser = ApiController.addUser_prop("newUserEmail", "defaultPass", "newUserName", "newUserLastname");
        newUserEmail = newUser.getEmail();
        newUserName = newUser.getFirstName();

        LOGGER.info("Step #1: validate new user data");
        assertEquals(newUserEmail, propNewUserEmail, "Received email is different from the expected");
        soft.assertEquals(newUserName, propNewUserName, "Received email is different from the expected");
        soft.assertAll();
    }

    public void completePostconditions(){
        LOGGER.info("CleanUp: delete created user");
        ApiController.deleteUser("newUserEmail", "defaultPass");
    }
}
