package utils.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.auth.Auth;
import utils.config.Config;

import static io.restassured.RestAssured.given;

public class RestProcessing {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessing.class);
    private static String token;
    private static String authEndpoint = "/users/login";
    private String serviceUrl;

    public RestProcessing() {
        serviceUrl = Config.getConfigProperty("baseUrl");
        RestAssured.baseURI = serviceUrl;
    }

    public static String getAuthToken(String usernameProp, String passwordProp){
        String username = Auth.getCredByName(usernameProp);
        String password = Auth.getCredByName(passwordProp);
        LOGGER.info("Get auth token for credentials: " + username + " " + password);

        String requestBody_auth = "{\"email\": \"" + username + "\", \"password\": \"" + password + "\"}";
        Response responseAuth = given()
                .contentType(ContentType.JSON)
                .body(requestBody_auth)
                .when()
                .post(authEndpoint);

        token = responseAuth.jsonPath().getString("token");

        return token;
    }

    public static Response sendGetReq(String url){
        LOGGER.info("Send get request at endpoint: " + url);
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .get(url);

        return response;
    }

}
