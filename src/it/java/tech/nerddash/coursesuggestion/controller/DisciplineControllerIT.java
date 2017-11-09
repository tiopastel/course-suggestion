package tech.nerddash.coursesuggestion.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.coursesuggestion.model.Discipline;

public class DisciplineControllerIT extends AbstractRestApiIT {

	private Discipline discipline;
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {

		discipline = new Discipline();
		discipline.setName("Server Implementation");

		entityObject = discipline;

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
