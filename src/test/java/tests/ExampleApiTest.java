package tests;

import chris.lim.helpers.ResponseHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Attachment;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Example API tests using Rest Assured and Hamcrest.
 */
public class ExampleApiTest extends ApiTestBase {

    @Test
    @Description("Test retrieving users from the /users endpoint")
    public void testGetUsers() {
        Response response = sendGetRequest("/users?page=2");

        validateGetUsersResponse(response);
    }

    @Test
    @Description("Test creating a new user with the /users endpoint")
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"John\", \"job\": \"leader\" }";

        Response response = sendPostRequest("/users", requestBody);

        validateCreateUserResponse(response, "John", "leader");
    }

    @Test
    @Description("Test updating a user's job with the /users/2 endpoint")
    public void testUpdateUser() {
        String requestBody = "{ \"name\": \"John\", \"job\": \"zion resident\" }";

        Response response = sendPutRequest("/users/2", requestBody);

        validateUpdateUserResponse(response, "zion resident");
    }

    @Test
    @Description("Test deleting a user with the /users/2 endpoint")
    public void testDeleteUser() {
        Response response = sendDeleteRequest("/users/2");

        validateDeleteUserResponse(response);
    }

    @Step("Send GET request to {endpoint}")
    private Response sendGetRequest(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint);
    }

    @Step("Send POST request to {endpoint}")
    private Response sendPostRequest(String endpoint, String requestBody) {
        attachRequestBody(requestBody);
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    @Step("Send PUT request to {endpoint}")
    private Response sendPutRequest(String endpoint, String requestBody) {
        attachRequestBody(requestBody);
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(endpoint);
    }

    @Step("Send DELETE request to {endpoint}")
    private Response sendDeleteRequest(String endpoint) {
        return RestAssured.given()
                .when()
                .delete(endpoint);
    }

    @Step("Validate GET users response")
    private void validateGetUsersResponse(Response response) {
        assertThat(ResponseHelper.getStatusCode(response), is(200));
        assertThat(response.jsonPath().getList("data"), hasSize(greaterThan(0)));
        assertThat(response.jsonPath().getString("data[0].first_name"), equalTo("Michael"));
        attachResponseBody(response.getBody().asString());
    }

    @Step("Validate create user response")
    private void validateCreateUserResponse(Response response, String expectedName, String expectedJob) {
        assertThat(ResponseHelper.getStatusCode(response), is(201));
        assertThat(response.jsonPath().getString("name"), equalTo(expectedName));
        assertThat(response.jsonPath().getString("job"), equalTo(expectedJob));
        attachResponseBody(response.getBody().asString());
    }

    @Step("Validate update user response")
    private void validateUpdateUserResponse(Response response, String expectedJob) {
        assertThat(ResponseHelper.getStatusCode(response), is(200));
        assertThat(response.jsonPath().getString("job"), equalTo(expectedJob));
        attachResponseBody(response.getBody().asString());
    }

    @Step("Validate delete user response")
    private void validateDeleteUserResponse(Response response) {
        assertThat(ResponseHelper.getStatusCode(response), is(204));
    }

    @Attachment(value = "Request Body", type = "application/json")
    private String attachRequestBody(String requestBody) {
        return requestBody;
    }

    @Attachment(value = "Response Body", type = "application/json")
    private String attachResponseBody(String responseBody) {
        return responseBody;
    }
}
