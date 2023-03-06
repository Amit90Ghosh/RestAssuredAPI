package bookAPI;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class Create_user {
	
	Faker fake = new Faker();
	//static String name ="";

	@Test
	void userCreation(ITestContext context) {
		
		//String accessToken;
		Create_User_Pojo cu = new Create_User_Pojo();
		String clientname = fake.name().firstName();
		cu.setClientName(clientname);
		cu.setClientEmail(fake.internet().emailAddress());
		
		
		Response res = given()
			.pathParam("client", "api-clients")
			.contentType("application/json")
			.body(cu)
			
		.when()
			.post("https://simple-books-api.glitch.me/{client}");
			
		Assert.assertEquals(res.statusCode(), 201);
		context.setAttribute("TokenNumber", res.jsonPath().get("accessToken"));
		context.setAttribute("name", clientname);
		
	}
}
