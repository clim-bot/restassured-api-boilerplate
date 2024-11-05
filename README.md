
# Rest Assured API Test Framework

## Overview

This project is a sample framework for testing REST APIs using Rest Assured, TestNG, and Allure for reporting. It includes examples of common API operations such as GET, POST, PUT, and DELETE with detailed test execution reports generated using Allure.

## Features

- **REST Assured**: For API testing.
- **TestNG**: For structuring and running tests.
- **Hamcrest**: For assertions.
- **Allure**: For generating comprehensive and detailed test reports.

## Prerequisites

- Java 8 or higher
- Maven 3.x
- Allure Command Line Tool

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/clim-bot/restassured-api-boilerplate.git
   cd restassured-api-boilerplate
   ```

2. Install dependencies and run tests:

   ```bash
   mvn clean test
   ```

3. Serve the Allure report:

   ```bash
   mvn allure:serve
   ```

## Project Structure

```
rest-assured-api-test-framework/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── config/
│   │   │   │   └── Config.java
│   │   │   ├── helpers/
│   │   │   │   ├── JsonHelper.java
│   │   │   │   ├── JwtTokenHelper.java
│   │   │   │   └── ResponseHelper.java
│   └── test/
│       ├── java/
│       │   └── tests/
│       │       ├── ApiTestBase.java
│       │       ├── ExampleApiTest.java
│       │       └── AuthApiTest.java
│       └── resources/
│           └── testng.xml
│
├── .gitignore
├── pom.xml
└── README.md
```

## Running Tests

To run the tests, execute:

```bash
mvn clean test
```

## Generating Allure Reports

To generate and serve the Allure report:

```bash
mvn allure:serve
```

## Allure Annotations

You can enhance test reports using Allure annotations:

- `@Description`: Provides a detailed description of the test.
- `@Step`: Marks a specific step within the test.
- `@Attachment`: Attaches files or data (e.g., request/response bodies) to the report.
- `@Epic`, `@Feature`, `@Story`: Organize and structure test cases within the Allure report.

Example usage:

```java
import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class ExampleApiTest extends ApiTestBase {

    @Test
    @Description("Test retrieving users from the /users endpoint")
    public void testGetUsers() {
        Response response = sendGetRequest("/users?page=2");

        validateGetUsersResponse(response);
    }

    @Step("Send GET request to {endpoint}")
    private Response sendGetRequest(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint);
    }
}
```

## Dependencies

- Rest Assured
- TestNG
- Allure
- Hamcrest
- Jackson

## Contribution

Feel free to fork the project and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

