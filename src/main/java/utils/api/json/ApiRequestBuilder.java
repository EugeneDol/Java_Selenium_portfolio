package utils.api.json;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class ApiRequestBuilder {


    public static String getRequestBody_auth(String username, String password){
        return "{\"email\": \"" + username + "\", \"password\": \"" + password + "\"}";
    }

    public static Headers getRequestHeader_auth(String token){
        return new Headers(new Header("Authorization", "Bearer " + token));
    }
}
