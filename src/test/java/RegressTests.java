import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
public class RegressTests {
    @Test
    void createUserTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void updateUserTest() {
        String data = "{ \"name\": \"alfa\"}";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/978")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("alfa"));
    }

    @Test
    void deleteUserTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .delete("https://reqres.in/api/users/978")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void listUsersTest() {

        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }
    @Test
    void singleUserTest() {

        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/7")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is("Michael"));
    }
}
