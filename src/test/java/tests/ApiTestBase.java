package tests;

import chris.lim.config.Config;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

/**
 * Base class for API tests, setting up the common configuration.
 */
public class ApiTestBase {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Config.getProperty("base.url");
    }
}
