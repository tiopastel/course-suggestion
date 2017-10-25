package tech.nerddash.coursesuggestion.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.coursesuggestion.model.Content;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Course.Level;
import tech.nerddash.coursesuggestion.model.Discipline;

public class CourseControllerTest extends AbstractRestApiTest{

	private Content content;
	private Discipline discipline;
	private List<Content> contents = new ArrayList<>();
	private List<Discipline> disciplines = new ArrayList<>();
	private Course course;
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {
		
		content = new Content();
		content.setName("Docker");
		content.setDescription(" Docker provides an additional layer of abstraction and automation of operating-system-level virtualization on Windows and Linux.");
		content.setJustification("Docker is a tecnologie used in many applications in the actual development cenario.");
		
		contents.add(content);
		
		discipline = new Discipline();
		discipline.setName("Server Implementation");
		discipline.setContents(contents);
		
		disciplines.add(discipline);
		
		course = new Course();
		course.setName("Web Development");
		course.setDisciplines(disciplines);		
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().post("/course").andReturn().jsonPath();
		
		
	}

	@After
	public void tearDown() throws Exception {
		given().get("/course/resetTable");
	}

	@Test
	public void testInsert() {
		
		course.setName("ADS");
		course.setLevel(Level.LOWER);
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().post("/course").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);
		
		assertEquals(2L, course.getId());
		assertEquals("ADS", course.getName());
		assertEquals(Level.LOWER, course.getLevel());

	}

	@Test
	public void testGet() {
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().get("/course/1").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);
		
		assertEquals(1L, course.getId());
		assertEquals("Web Development", course.getName());
		assertEquals(Level.UPPER, course.getLevel());
	}

	@Test
	public void testDelete() {
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().get("/course/1").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);
		
		assertEquals(1L, course.getId());
		assertEquals("Web Development", course.getName());
		assertEquals(Level.UPPER, course.getLevel());
	}

	@Test
	public void testUpdate() {
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().get("/course/1").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);
		
		course.setName("ADS");
		course.setLevel(Level.LOWER);
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().put("/course").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);
		
		assertEquals(1L, course.getId());
		assertEquals("ADS", course.getName());
		assertEquals(Level.LOWER, course.getLevel());
	}

}
