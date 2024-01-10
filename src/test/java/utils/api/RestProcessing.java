package utils.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestProcessing {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessing.class);
    private String serviceUrl;

    public String sendGetReq(){
        String response = given().
                param("key1", "value1").
                param("key2", "value2").
                when().
                post("/somewhere").
                then().
                body(containsString("OK")).toString();

        return response;
    }

}
