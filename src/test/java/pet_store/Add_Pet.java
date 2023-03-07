package pet_store;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

public class Add_Pet {

	
	JSONObject jo = new JSONObject();
	Faker fake = new Faker();
	String name = fake.animal().name();
	int id = fake.number().numberBetween(10, 999);
	
	@Test(priority=1)
	void addNewPet() {
		
		jo.put("id", id);
		jo.put("name", name);
		//jo.setCategory().setId(id);
		jo.put("status", "available");
		
		
		given()
			.contentType("application/json")
			.body(jo.toString())
		
		.when()
		    .post("https://petstore.swagger.io/v2/pet")
		    
		.then()
		    .statusCode(200)
		    .log().all();
	}
	
	@Test(priority=2)
	void FindPet() {
		
		
		given()
	
	.when()
	    .get("https://petstore.swagger.io/v2/pet/" + id)
	    
	.then()
	    .statusCode(200)
	    .log().all();
	}
}
