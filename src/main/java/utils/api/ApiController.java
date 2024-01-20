package utils.api;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.api.json.ApiRequestBuilder;
import utils.api.json.JsonParser;
import utils.auth.Auth;


public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessing.class);
    private static String authEndpoint = "/users/login";
    private static String userProfileEndpoint = "/users/me";
    private static RestProcessing restProcessing = new RestProcessing();

    public static String getAuthToken(String usernameProp, String passwordProp){
        String token;
        String username = Auth.getCredByName(usernameProp);
        String password = Auth.getCredByName(passwordProp);
        LOGGER.info("Get auth token for credentials: " + username + " " + password);

        String requestBody_auth = ApiRequestBuilder.getRequestBody_auth(username, password);
        Response responseAuth = restProcessing.sendPostReq(authEndpoint, requestBody_auth);

        token = JsonParser.getValueByKey(responseAuth, "token");

        return token;
    }

    public static String getUserProfile(String token){
        LOGGER.info("Get user profile with token: " + token);

        //Object user = new Object(); - implement returning whole user obj
        String user;
        Headers requestHeader_auth = ApiRequestBuilder.getRequestHeader_auth(token);
        Response responseUserProfile = restProcessing.sendGetReq( userProfileEndpoint, requestHeader_auth);

        user = JsonParser.getValueByKey(responseUserProfile, "email");

        return user;
    }

}
