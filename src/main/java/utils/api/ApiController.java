package utils.api;

import com.google.gson.Gson;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import obj.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.api.json.ApiRequestBuilder;
import utils.api.json.JsonParser;
import utils.auth.Auth;


public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessing.class);
    private static String addUserEndpoint = "/users";
    private static String loginEndpoint = "/users/login";
    private static String logoutEndpoint = "/users/logout";
    private static String userProfileEndpoint = "/users/me";
    private static RestProcessing restProcessing = new RestProcessing();

    public static String getAuthToken(String usernameProp, String passwordProp){
        String token;

        Response responseAuth = loginViaApi(usernameProp,passwordProp);
        token = JsonParser.getValueByKey(responseAuth, "token");

        return token;
    }

    public static Response loginViaApi(String usernameProp, String passwordProp){
        String username = Auth.getCredByName(usernameProp);
        String password = Auth.getCredByName(passwordProp);
        LOGGER.info("Get auth token for credentials: " + username + " " + password);

        String requestBody_auth = ApiRequestBuilder.getRequestBody_auth(username, password);
        Response responseAuth = restProcessing.sendPostReq(loginEndpoint, requestBody_auth);

        return responseAuth;
    }

    public static void logoutViaApi(String token){
        LOGGER.info("Logout with the token: " + token);

        Headers requestHeader_auth = ApiRequestBuilder.getRequestHeader_auth(token);
        String requestBody_logout = ApiRequestBuilder.getRequestBody_empty();
        restProcessing.sendPostReq(logoutEndpoint, requestBody_logout, requestHeader_auth);
    }

    public static User getUserProfile(String token){
        LOGGER.info("Get user profile with token: " + token);

        Headers requestHeader_auth = ApiRequestBuilder.getRequestHeader_auth(token);
        Response responseUserProfile = restProcessing.sendGetReq( userProfileEndpoint, requestHeader_auth);

        User userProfile = new Gson().fromJson(responseUserProfile.asString(), User.class);

        return userProfile;
    }

    public static User addUser_val(String email, String password, String name, String lastname){
        LOGGER.info("Add user with email: " + email);

        String requestBody_addUser = ApiRequestBuilder.getRequestBody_addUser(email, password, name, lastname);
        Response responseUser = restProcessing.sendPostReq(addUserEndpoint, requestBody_addUser);
        User createdUser = new Gson().fromJson(responseUser.jsonPath().get("user").toString(), User.class);

        return createdUser;
    }

    public static User addUser_prop(String emailProp, String passwordProp, String nameProp, String lastnameProp){

        String email = Auth.getCredByName(emailProp);
        String password = Auth.getCredByName(passwordProp);
        String name = Auth.getCredByName(nameProp);
        String lastname = Auth.getCredByName(lastnameProp);

        User createdUser = addUser_val(email, password, name, lastname);

        return createdUser;
    }

    public static void deleteUser(String usernameProp, String passwordProp){
        String token = getAuthToken(usernameProp,passwordProp);

        Headers requestHeader_auth = ApiRequestBuilder.getRequestHeader_auth(token);
        restProcessing.sendDeleteReq( userProfileEndpoint, requestHeader_auth);
    }

}
