package eu.senla.lab.api;

import eu.senla.lab.utils.ConfigLoader;
import eu.senla.lab.utils.Log;
import io.restassured.response.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static eu.senla.lab.constants.CoreConstants.BASE_URI;
import static io.restassured.RestAssured.given;

public class Authorize {

    private static String xapp_token;
    private static String access_token;
    private static LocalDateTime expires_at;
    private static LocalDateTime expires_in;
    private static LocalDateTime currentTime = LocalDateTime.now();


    public static String getXapp_token() {
        if (xapp_token == null || currentTime.isAfter(expires_at)) {
            Log.info("Start to refresh xapp_token");
            Response response = refreshXappToken();
            xapp_token = response.path("token");
            String time = response.path("expires_at");
            String correctTime = time.substring(0, 19);
            expires_at = LocalDateTime.parse(correctTime);
            Log.info("xapp_token refreshed");
        }
        return xapp_token;
    }

    public static String getAccess_token()  {
        if (access_token == null || currentTime.isAfter(expires_in)) {
            Log.info("Start to refresh access_token");
            Response response = null;
            try {
                response = refreshAccessToken();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            access_token = response.path("access_token");
            String time = response.path("expires_in");
            String correctTime = time.substring(0, 19);
            expires_in = LocalDateTime.parse(correctTime);
            Log.info("access_token refreshed");
        }
        return access_token;
    }

    private static Response refreshXappToken()  {
        Map<String, String> map = new HashMap<>();
        try {
            map.put("client_id", ConfigLoader.getInstance().getClientId());
            map.put("client_secret", ConfigLoader.getInstance().getClientSecret());
            return given().
                    baseUri(BASE_URI).
                    params(map).
                    when().
                    post("/tokens/xapp_token").
                    then().statusCode(201).extract().response();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Response refreshAccessToken() throws IOException {
        return given().
                baseUri("https://api.artsy.net/")
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("grant_type", ConfigLoader.getInstance().getGrantType())
                .formParam("email", ConfigLoader.getInstance().getEmail())
                .formParam("password", ConfigLoader.getInstance().getPassword())
                .formParam("scope", "openid email")
                .formParam("client_id", ConfigLoader.getInstance().getClientId())
                .formParam("client_secret", ConfigLoader.getInstance().getClientSecret()).
        when().
                post("oauth2/access_token").
        then().
                statusCode(201).extract().response();
    }


}
