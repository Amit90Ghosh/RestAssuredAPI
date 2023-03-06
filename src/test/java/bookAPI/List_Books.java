package bookAPI;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class List_Books {

	@Test
	void displayBooks() {
		
		given()
			.pathParam("path", "books")
			.queryParam("type", "non-fiction")
			
		.when()
			.get("https://simple-books-api.glitch.me/{path}")
			
		.then()
			.statusCode(200)
			.header("Content-Type", equalTo("application/json; charset=utf-8"))
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("books.json"));
			
	}
	
}
