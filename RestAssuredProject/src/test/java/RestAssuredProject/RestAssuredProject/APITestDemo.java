package RestAssuredProject.RestAssuredProject;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APITestDemo {
	
	@Test(priority=1)
	//Get the pets with available status
	public void getTest() {
		
		 given().
		    when().
		        get("https://petstore.swagger.io/v2/pet/findByStatus?status=available").
		    then().
		        assertThat().
		        statusCode(200).
		    and().
		       log().body();
		 
	}
	
	@Test(priority = 2)
	//Post a new pet
	public void postTest() {
		

		HashMap<String,Object> data = new HashMap();
		data.put("id", 121);
		HashMap<String,Object> catData = new HashMap();
		catData.put("id", 121);
		catData.put("name", "Pet Category");
		data.put("category", catData);
		data.put("name", "doggie");
		List<String> photoList = new ArrayList();
		data.put("photoUrls", photoList);
		List<Object> tagsList = new ArrayList();
		
		Map<String,Object> tagData = new HashMap();
		tagData.put("id", 121);
		tagData.put("name", "Golden retriever");
		tagsList.add(tagData);
		data.put("tags", tagsList);
		data.put("status", "available");
		
		
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://petstore.swagger.io/v2/pet")
		.then()
			.assertThat()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		
	}
	
	
	@Test(priority=3)
	//Update the newly pet
	public void updateTest() {
	
	HashMap<String,Object> data = new HashMap();
	data.put("id", 121);
	HashMap<String,Object> catData = new HashMap();
	catData.put("id", 121);
	catData.put("name", "Pet Category");
	data.put("category", catData);
	data.put("name", "doggie");
	List<String> photoList = new ArrayList();
	data.put("photoUrls", photoList);
	List<Object> tagsList = new ArrayList();
	
	Map<String,Object> tagData = new HashMap();
	tagData.put("id", 121);
	tagData.put("name", "Golden retriever");
	tagsList.add(tagData);
	data.put("tags", tagsList);
	data.put("status", "sold");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://petstore.swagger.io/v2/pet")
		.then()
			.assertThat()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		
		
	}
	//Delete this api
	@Test(priority = 4)
	public void deleteUpdatedAPI() {
		given()
		.when()
			.delete("https://petstore.swagger.io/v2/pet/121")
		.then()
		.assertThat()
		.statusCode(200)
		.log().body()
		.extract().response();
	}
}
