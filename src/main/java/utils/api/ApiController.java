package utils.api;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.api.json.JsonBodyBuilder;
import utils.api.json.JsonParser;
import utils.auth.Auth;


public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessing.class);
    private static String token;
    private static String authEndpoint = "/users/login";
    private static RestProcessing restProcessing = new RestProcessing();

    public static String getAuthToken(String usernameProp, String passwordProp){
        String username = Auth.getCredByName(usernameProp);
        String password = Auth.getCredByName(passwordProp);
        LOGGER.info("Get auth token for credentials: " + username + " " + password);

        String requestBody_auth = JsonBodyBuilder.getRequestBody_auth(username, password);
        Response responseAuth = restProcessing.sendPostReq(authEndpoint, requestBody_auth);

        token = JsonParser.getValueByKey(responseAuth, "token");

        return token;
    }

}
