package chris.lim.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.restassured.response.Response;

/**
 * Utility class for JWT token handling.
 */
public class JwtTokenHelper {

    /**
     * Creates a JWT token with the specified secret and subject.
     *
     * @param secret  the secret key
     * @param subject the subject
     * @return the generated JWT token
     */
    public static String createToken(String secret, String subject) {
        return JWT.create()
                .withSubject(subject)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * Extracts the JWT token from the response body.
     *
     * @param response the API response
     * @return the JWT token as a String
     */
    public static String extractToken(Response response) {
        return response.jsonPath().getString("token");
    }
}
