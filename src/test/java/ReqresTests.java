import lombok.RegisterData;
import lombok.UserWithJob;
import models.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specifications.Specs.request;
import static specifications.Specs.responseSpec;

public class ReqresTests {

    //Using Groovy
    @Test
    @DisplayName("GET List user")
    void getListUser() {
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .when()
                .get("/users?page=2")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("lindsay.ferguson@reqres.in"));
    }

    //Using Model
    @Test
    @DisplayName("GET Single user")
    void getSingleUser() {
        UserData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(UserData.class);
        assertEquals(2, data.getData().getId());
        assertEquals("janet.weaver@reqres.in", data.getData().getEmail());
        assertEquals("Janet", data.getData().getFirstName());
        assertEquals("Weaver", data.getData().getLastName());
    }

    @Test
    @DisplayName("GET Single user not found")
    void getSingleUserNotFound() {
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .when()
                .get("/users/23")
                .then()
                .log().body()
                .statusCode(404);
    }

    //Using Lombok Model
    @Test
    @DisplayName("POST Create user")
    void postCreateUser() {
        UserWithJob userWithJob = UserWithJob.builder()
                .name("morpheus")
                .job("leader")
                .build();
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .body(userWithJob)
                .when()
                .post("/users")
                .then()
                .log().body()
                .body("name", is(userWithJob.getName()))
                .body("job", is(userWithJob.getJob()))
                .body("id", is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }

    @Test
    @DisplayName("PUT update user")
    void putUpdateUser() {
        UserWithJob userWithJob = UserWithJob.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .body(userWithJob)
                .when()
                .put("/users/2")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("name", is(userWithJob.getName()))
                .body("job", is(userWithJob.getJob()))
                .body("updatedAt", is(notNullValue()));
    }

    @Test
    @DisplayName("PATCH Update user")
    void patchUpdateUser() {
        UserWithJob userWithJob = UserWithJob.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .body(userWithJob)
                .when()
                .patch("/users/2")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("name", is(userWithJob.getName()))
                .body("job", is(userWithJob.getJob()))
                .body("updatedAt", is(notNullValue()));
    }

    @Test
    @DisplayName("DELETE user")
    void deleteUser() {
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .delete("/users/2")
                .then()
                .log().body()
                .statusCode(204);
    }

    @Test
    @DisplayName("POST Successful register")
    void successfulRegister() {
        RegisterData registerData = RegisterData.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .body(registerData)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("id", is(notNullValue()))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("POST Unsuccessful register")
    void unsuccessfulRegister() {
        RegisterData registerData = RegisterData.builder()
                .email("sydney@fife")
                .build();
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .body(registerData)
                .when()
                .post("/register")
                .then()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @DisplayName("POST Successful login")
    void successfulLogin() {
        RegisterData registerData = RegisterData.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .body(registerData)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("POST Unsuccessful login")
    void unsuccessfulLogin() {
        RegisterData registerData = RegisterData.builder()
                .email("peter@klaven")
                .build();
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .body(registerData)
                .when()
                .post("/login")
                .then()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
