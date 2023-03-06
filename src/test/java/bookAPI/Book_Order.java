package bookAPI;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Book_Order {

	@Test
	void orderBook(ITestContext context) {
		
		String BearToken = (String) context.getAttribute("TokenNumber");
		//System.out.println(BearToken);
		Book_Pojo bj = new Book_Pojo();
		bj.setCustomerName((String) context.getAttribute("name"));
		//System.out.println((String) context.getAttribute("name"));
		bj.setBookId(5);
		
		given()
			.headers("Authorization", "Bearer " + BearToken)
			.pathParam("book", "orders")
			.contentType("application/json")
			.body(bj)
			
		.when()
			.post("https://simple-books-api.glitch.me/{book}")
			
		.then()
			.statusCode(201)
			.log().all();
			
		
	}
	
}
