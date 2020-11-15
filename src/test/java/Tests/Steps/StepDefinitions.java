package Tests.Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class StepDefinitions {

    String userId;
    String username = "ben6389";
    String password = "Test@1234";
    Response response;

    @Given("^create a user with valid username and password$")
    public void create_a_user_with_valid_username_and_password() throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"userName\":\"" + username + "\",\"password\":\"" + password + "\"}";
        System.out.println(payload);

        response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("Account/v1/User")
                .then().assertThat().statusCode(201)
                .extract()
                .response();
    }

    @Given("^I enter the Username and Password$")
    public void i_enter_the_username_and_password() throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"userName\":\"" + username + "\",\"password\":\"" + password + "\"}";

        response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/Account/v1/Login")
                .then().assertThat().statusCode(200)
                .extract()
                .response();

        System.out.println(response.jsonPath().getString("userId"));
        userId = response.jsonPath().getString("userId");
    }

    @And("^Generate a token for that user$")
    public void generate_a_token_for_that_user() throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"userName\":\"" + username + "\",\"password\":\"" + password + "\"}";

        response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/Account/v1/GenerateToken")
                .then().assertThat().statusCode(200)
                .extract()
                .response();

        System.out.println(response.asString());
    }

    @Then("^Add new book with valid ISBN \"([^\"]*)\" to the collection$")
    public void add_new_book_with_valid_isbn_something_to_the_collection(String isbn) throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"userId\":\"" + userId + "\",\"collectionOfIsbns\":[{\"isbn\":\"" + isbn + "\"}]}";

        System.out.println(payload);

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .extract()
                .response();

        System.out.println(response.asString());
    }

    @Then("^Add new book with invalid ISBN \"([^\"]*)\" to the collection$")
    public void add_new_book_with_invalid_isbn_something_to_the_collection(String isbn) throws Throwable {
        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"userId\":\"" + userId + "\",\"collectionOfIsbns\":[{\"isbn\":\"" + isbn + "\"}]}";

        System.out.println(payload);

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .extract()
                .response();

        System.out.println(response.asString());
    }

    @Then("^Replace existing book ISBN \"([^\"]*)\" with new book ISBN \"([^\"]*)\" in the collection$")
    public void replace_existing_book_isbn_something_with_new_book_isbn_something_in_the_collection(String isbn1, String isbn2)
            throws Throwable {
        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"isbn\":\"" + isbn2 + "\", \"userId\":\"" + userId + "\"}";

        System.out.println(payload);

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/BookStore/v1/Books/" + isbn1)
                .then()
                .extract()
                .response();

        System.out.println(response.asString());

    }

    @Then("^Replace existing invalid book ISBN \"([^\"]*)\" with new book ISBN \"([^\"]*)\" in the collection$")
    public void replace_existing_invalid_book_isbn_something_with_new_book_isbn_something_in_the_collection(String oldisbn, String newisbn)
            throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"isbn\":\"" + newisbn + "\", \"userId\":\"" + userId + "\"}";

        System.out.println(payload);

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/BookStore/v1/Books/" + oldisbn)
                .then()
                .extract()
                .response();

        System.out.println(response.asString());
    }

    @Then("^Get book details in the collection with valid isbn \"([^\"]*)\"$")
    public void get_book_details_in_the_collection_with_valid_isbn_something(String isbn) throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .when()
                .get("BookStore/v1/Book?ISBN=" + isbn)
                .then()
                .extract()
                .response();

        System.out.println(response.asString());

    }

    @Then("^Get book details in the collection with invalid isbn \"([^\"]*)\"$")
    public void get_book_details_in_the_collection_with_invalid_isbn_something(String isbn) throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .when()
                .get("BookStore/v1/Book?ISBN=" + isbn)
                .then()
                .extract()
                .response();

        System.out.println(response.asString());
    }

    @Then("^Delete a book from the collection with valid isbn \"([^\"]*)\"$")
    public void delete_a_book_from_the_collection_with_valid_isbn_something(String isbn) throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"isbn\":\"" + isbn + "\", \"userId\":\"" + userId + "\"}";

        System.out.println(payload);

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .extract()
                .response();
        System.out.println(response.asString());
    }

    @Then("^Delete a book from the collection with invalid isbn \"([^\"]*)\"$")
    public void delete_a_book_from_the_collection_with_invalid_isbn_something(String isbn) throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        String payload = "{\"isbn\":\"" + isbn + "\", \"userId\":\"" + userId + "\"}";

        System.out.println(payload);

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .extract()
                .response();
        System.out.println(response.asString());
    }

    @Then("^Delete the user$")
    public void delete_the_user() throws Throwable {

        RestAssured.baseURI = "https://demoqa.com";

        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .delete("Account/v1/User/" + userId)
                .then()
                .extract()
                .response();
        System.out.println(response.asString());
    }

    @And("^Check if the status code is \"([^\"]*)\"$")
    public void check_if_the_status_code_is_something(String statusCode) throws Throwable {
         Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
    }
}



