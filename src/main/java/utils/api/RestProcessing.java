package utils.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static Response sendPostReq(String endpoint, String body){

        LOGGER.info("Send post request at endpoint: " + endpoint  + "\nwith body: " + body);
        Response response = given()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post(endpoint);


        return response;
    }

    public static Response sendPostReq(String endpoint, String body, Headers header){

        LOGGER.info("Send post request at endpoint: " + endpoint + "\nwith headers: " + header + "\nwith body: " + body);
        Response response = given()
                .contentType(ContentType.JSON)
                .headers(header)
                .body(body)
                .when()
                .post(endpoint);


        return response;
    }

    public static Response sendGetReq(String endpoint, Headers header){
        LOGGER.info("Send get request at endpoint: " + endpoint + " with headers: " + header);
        Response response = given()
                .headers(header)
                //.header("Authorization", "Bearer " + token)
                .get(endpoint);

        return response;
    }

}
