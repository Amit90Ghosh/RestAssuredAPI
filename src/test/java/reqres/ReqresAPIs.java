package reqres;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ReqresAPIs {

	String id;
	@Test(priority=1)
	void getListUsers() {
		
		given()
		
		.when()
		 .get("https://reqres.in/api/users?page=2")
		 
		.then()
		.statusCode(200)
		.body("per_page", equalTo(6))
		.body("data[3].id", equalTo(10))
		.body("data[4].avatar", equalTo("https://reqres.in/img/faces/11-image.jpg"))
		.body("support.url", equalTo("https://reqres.in/#support-heading"))
		.log().all()
		.header("Content-Type", equalTo("application/json; charset=utf-8"));
		
	}
	
	@Test(priority=2)
	void getUser() {
		
		Reqres_Pojo pj = new Reqres_Pojo();
		Faker fake = new Faker();
		pj.setName(fake.name().fullName());
		pj.setJob(fake.job().title());
		
		
		Response res= given()	
		.contentType("application/json")
		.body(pj)	
		.when()
			.post("https://reqres.in/api/users");
		
		Assert.assertEquals(res.statusCode(), 201);
		Assert.assertEquals(res.jsonPath().get("name"), pj.getName());
		id = res.jsonPath().get("id").toString();
		System.out.println(id);
		
	}
	
	@Test(priority=3)
	void updateUser() {
		
		Reqres_Pojo pj = new Reqres_Pojo();
		Faker fake = new Faker();
		//pj.setName(fake.name().fullName());
		pj.setJob(fake.job().title());
		
		given()
		.contentType("application/json")
		.body(pj)
		
		.when()
		.patch("https://reqres.in/api/users" + "/" + id)
		
		.then()
		.statusCode(200)
		.body("job", equalTo(pj.getJob()))
		.body("name", equalTo(pj.getName()))
		.log().all();
	}
}
