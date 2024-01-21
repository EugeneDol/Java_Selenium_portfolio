package utils.auth;

import utils.ReadProperties;

public class Auth extends ReadProperties {

    private static String CREDENTIALS_FILE = "src/main/resources/configs/users.properties";
    private static String getCredential(String fieldName) {
        return getProperty(CREDENTIALS_FILE, fieldName);
    }

    public static String getDefaultUsername() {
        return getCredential("defaultUsername");
    }
    public static String getDefaultPassword() {
        return getCredential("defaultPass");
    }

    public static String getCredByName(String credName){
        return getCredential(credName);
    }

}
