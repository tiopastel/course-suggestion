package tech.nerddash.coursesuggestion.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.coursesuggestion.model.Content;

public class ContentControllerIT extends AbstractRestApiIT {

	private Content content;
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {

		content = new Content();
		content.setName("Docker");
		content.setDescription(
				" Docker provides an additional layer of abstraction and automation of operating-system-level virtualization on Windows and Linux.");
		content.setJustification("Docker is a tecnologie used in many applications in the actual development cenario.");

		entityObject = content;

	}

	@Test
	public void testCRUD() {

		// INSERT

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().post("/content").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		assertEquals(1L, content.getId());
		assertEquals("Docker", content.getName());

		// GET

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().get("/content/1").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		assertEquals(1L, content.getId());
		assertEquals("Docker", content.getName());

		// UPDATE

		content.setName("SQL");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().put("/content").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		assertEquals(1L, content.getId());
		assertEquals("SQL", content.getName());

		// DELETE

		retorno = given().header("Accept", "application/json").contentType("application/json").body(content).expect()
				.statusCode(200).when().delete("/content/1").andReturn().jsonPath();

		content = retorno.getObject("content", Content.class);

		assertEquals(1L, content.getId());
		assertEquals(null, content.getName());
	}

}
