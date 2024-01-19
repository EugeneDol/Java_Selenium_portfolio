package utils.api.json;

public class JsonBodyBuilder {


    public static String getRequestBody_auth(String username, String password){
        return "{\"email\": \"" + username + "\", \"password\": \"" + password + "\"}";
    }
}
