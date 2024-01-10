package utils.api;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestProcessing {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessing.class);
    private String serviceUrl;

    public String sendGetReq(String url){
        String response = given()
                .when()
                .get(url)
                .toString();

        return response;
    }

}
