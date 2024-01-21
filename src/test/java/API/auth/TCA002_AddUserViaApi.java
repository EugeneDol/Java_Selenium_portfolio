package API.auth;

import annotations.Records;
import obj.User;
import org.testng.annotations.Test;
import utils.api.ApiController;
import utils.auth.Auth;
import utils.baseTest.BaseTest_API;

import static org.testng.Assert.assertEquals;

public class TCA002_AddUserViaApi extends BaseTest_API {
    private User newUser;
    private String propNewUserEmail;
    private String propNewUserName;

    @Records(
            creator = "Eugene Dolbik",
            creationDate = "21/01/2024",
            manualTCLink = "",
            functionalityTag = "registration"
    )

    public void completePreconditions(){
        propNewUserEmail = Auth.getCredByName("newUserEmail");
        propNewUserName = Auth.getCredByName("newUserName");

    }

    @Test
    public void TCA002_AddUserViaApi(){
        newUser = ApiController.addUser_prop("newUserEmail", "defaultPass", "newUserName", "newUserLastname");

        assertEquals(newUser.getEmail(), propNewUserEmail, "Received email is different from the expected");
        soft.assertEquals(newUser.getFirstName(), propNewUserName, "Received email is different from the expected");
        soft.assertAll();
    }

    public void completePostconditions(){
        ApiController.deleteUser("newUserEmail", "defaultPass");
    }
}
