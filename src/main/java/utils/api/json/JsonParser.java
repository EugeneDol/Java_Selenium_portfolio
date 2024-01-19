package utils.api.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.response.Response;

public class JsonParser {

    public static String getValueByKey(String json, String key) {
        JsonElement jsonElement = new Gson().fromJson(json, JsonObject.class).get(key);
        String value = "";

        if (jsonElement != null) {
            if (!jsonElement.isJsonObject() && !jsonElement.isJsonArray() && !jsonElement.isJsonNull()) {
                value = jsonElement.getAsString();
            } else {
                value = jsonElement.toString();
            }
        }

        return value;
    }

    public static String getValueByKey(Response json, String key) {
        String jsonString = json.asString();
        return getValueByKey(jsonString, key);
    }

}
