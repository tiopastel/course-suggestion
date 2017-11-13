package tech.nerddash.coursesuggestion.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.coursesuggestion.model.Content;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;

public class ContentControllerIT extends AbstractRestApiIT {

	private Content content;
	private Discipline discipline;
	private JsonPath retorno;
	private Course course;

	@Before
	public void setUp() throws Exception {

		// Creates a new Course

		course = new Course();

		course.setName("A dummy course");

		course.setDescription("this is just a dummy course");

		course.setJustification("Because We can");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().post("/course").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);

		// Creates a new Discipline

		discipline = new Discipline();
		discipline.setName("Server Implementation");
		discipline.setCourse(course);

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().post("/discipline").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		// Creates a new Content

		content = new Content();
		content.setName("Docker");
		content.setDescription("Docker provides an additional layer of abstraction and automation of operating-system-level virtualization on Windows and Linux.");
		content.setJustification("Docker is a tecnologie used in many applications in the actual development cenario.");

		content.setDiscipline(discipline);

	}

	@After
	public void tearDown() throws Exception {
		given().expect().statusCode(200).when().get("/content/resetTable");
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
