package tech.nerddash.coursesuggestion.controller;



import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.coursesuggestion.model.Content;


public class ContentControllerTest extends AbstractRestApiTest {

	private Content content;
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {
		

		content = new Content();
		content.setName("Docker");
		content.setDescription(
				" Docker provides an additional layer of abstraction and automation of operating-system-level virtualization on Windows and Linux.");
		content.setJustification("Docker is a tecnologie used in many applications in the actual development cenario.");


		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().post("/content").andReturn().jsonPath();

	}


	@Test
	public void testInsert() {

		content.setName("SQL");
	

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().post("/content").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		assertEquals(2L, content.getId());
		assertEquals("SQL", content.getName());

	}

	@Test
	public void testGet() {

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().get("/content/1").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		assertEquals(1L, content.getId());
		assertEquals("Docker", content.getName());
	
	}

	@Test
	public void testDelete() {

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().get("/content/1").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		assertEquals(1L, content.getId());
		assertEquals("Docker", content.getName());
	}

	@Test
	public void testUpdate() {

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().get("/content/1").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		content.setName("SQL");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().put("/content").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		assertEquals(1L, content.getId());
		assertEquals("SQL", content.getName());
	}

}
