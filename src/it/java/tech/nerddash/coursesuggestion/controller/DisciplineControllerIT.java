package tech.nerddash.coursesuggestion.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;

public class DisciplineControllerIT extends AbstractRestApiIT {

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
	}

	@After
	public void tearDown() throws Exception {
		given().expect().statusCode(200).when().get("/discipline/resetTable");
	}

	@Test
	public void testCRUD() {

		// INSERT

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().post("/discipline").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		assertEquals(1L, discipline.getId());
		assertEquals("Server Implementation", discipline.getName());

		// GET

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().get("/discipline/1").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		assertEquals(1L, discipline.getId());
		assertEquals("Server Implementation", discipline.getName());

		// UPDATE

		discipline.setName("SQL");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().put("/discipline").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		assertEquals(1L, discipline.getId());
		assertEquals("SQL", discipline.getName());

		// DELETE

		retorno = given().header("Accept", "application/json").contentType("application/json").body(discipline).expect()
				.statusCode(200).when().delete("/discipline/1").andReturn().jsonPath();

		discipline = retorno.getObject("discipline", Discipline.class);

		assertEquals(1L, discipline.getId());
		assertEquals(null, discipline.getName());
	}

}
