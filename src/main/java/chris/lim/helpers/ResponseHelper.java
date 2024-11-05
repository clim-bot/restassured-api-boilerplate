package chris.lim.helpers;

import io.restassured.response.Response;

/**
 * Utility class for processing API responses.
 */
public class ResponseHelper {

    /**
     * Retrieves a value from a JSON path in the response.
     *
     * @param response the API response
     * @param key      the JSON path key
     * @return the value at the specified JSON path
     */
    public static String getJsonPath(Response response, String key) {
        return response.jsonPath().getString(key);
    }

    /**
     * Gets the status code from the response.
     *
     * @param response the API response
     * @return the status code
     */
    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }
}
