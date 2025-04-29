package util;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.UUID;

public class ApiClient {
    private static final String BASE_URI = "https://api.todoist.com/rest/v9";
    private static final String TOKEN = "080e48d85046e0dd983a8344535169aee62b2536";

    public static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .auth().oauth2(TOKEN)
                .header("X-Request-Id", UUID.randomUUID().toString())
                .contentType("application/json");
    }
}
