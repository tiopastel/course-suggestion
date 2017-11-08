package tech.nerddash.coursesuggestion.controller;



import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.coursesuggestion.model.Content;
import tech.nerddash.coursesuggestion.model.Discipline;


public class DisciplineControllerTest extends AbstractRestApiTest {

	private Content content;
	private Discipline discipline;
	private List<Content> contents = new ArrayList<>();
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {

		content = new Content();
		content.setName("Docker");
		content.setDescription(
				" Docker provides an additional layer of abstraction and automation of operating-system-level virtualization on Windows and Linux.");
		content.setJustification("Docker is a tecnologie used in many applications in the actual development cenario.");

		contents.add(content);

		discipline = new Discipline();
		discipline.setName("Server Implementation");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().post("/discipline").andReturn().jsonPath();

	}

	@After
	public void tearDown() throws Exception {
		given().get("/discipline/resetTable");
	}

	@Test
	public void testInsert() {

		discipline.setName("SQL");
	

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().post("/discipline").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		assertEquals(2L, discipline.getId());
		assertEquals("SQL", discipline.getName());

	}

	@Test
	public void testGet() {

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().get("/discipline/1").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		assertEquals(1L, discipline.getId());
		assertEquals("Server Implementation", discipline.getName());
	
	}

	@Test
	public void testDelete() {

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().get("/discipline/1").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		assertEquals(1L, discipline.getId());
		assertEquals("Server Implementation", discipline.getName());
	}

	@Test
	public void testUpdate() {

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().get("/discipline/1").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		discipline.setName("SQL");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().put("/discipline").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		assertEquals(1L, discipline.getId());
		assertEquals("SQL", discipline.getName());
	}

}
