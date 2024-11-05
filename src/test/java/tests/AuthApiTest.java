package tests;

import chris.lim.helpers.JwtTokenHelper;
import chris.lim.helpers.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Attachment;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * API tests for authentication-related operations.
 */
public class AuthApiTest extends ApiTestBase {

    @Test
    @Description("Test authentication with credentials and validate token extraction")
    public void testAuthWithCredentialsAndExtractToken() {
        Map<String, String> payload = prepareLoginPayload("eve.holt@reqres.in", "cityslicka");
        Response response = sendLoginRequest(payload);

        validateResponse(response);
        String token = JwtTokenHelper.extractToken(response);
        assertThat(token, notNullValue());

        logResponseBody(response.getBody().asString());
    }

    @Step("Prepare login payload with email: {email} and password")
    private Map<String, String> prepareLoginPayload(String email, String password) {
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);
        return payload;
    }

    @Step("Send login request")
    private Response sendLoginRequest(Map<String, String> payload) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(JsonHelper.toJson(payload))
                .when()
                .post("/login");
    }

    @Step("Validate response")
    private void validateResponse(Response response) {
        assertThat(response.getStatusCode(), is(200));
    }

    @Attachment(value = "Response Body", type = "application/json")
    private String logResponseBody(String responseBody) {
        return responseBody;  // The response body is logged as an attachment in the Allure report.
    }
}
