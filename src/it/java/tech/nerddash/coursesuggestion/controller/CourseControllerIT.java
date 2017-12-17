package tech.nerddash.coursesuggestion.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Course.Level;

public class CourseControllerIT extends AbstractRestApiIT {

	private Course course;
	private JsonPath retorno;
	private List<Course> courses;

	@Before
	public void setUp() throws Exception {

		// Creates a new Course

		course = new Course();

		course.setName("Web Development");

		course.setDescription(
				"Web development is a broad term for the work involved in developing a web site for the Internet (World Wide Web) or an intranet (a private network). Web development can range from developing the simplest static single page of plain text to the most complex web-based internet applications (or just 'web apps') electronic businesses, and social network services. A more comprehensive list of tasks to which web development commonly refers, may include web engineering, web design, web content development, client liaison, client-side/server-side scripting, web server and network security configuration, and e-commerce development. Among web professionals, \"web development\" usually refers to the main non-design aspects of building web sites: writing markup and coding. Most recently Web development has come to mean the creation of content management systems or CMS.");
		course.setJustification("Becouse We can");

		course.setLevel(Level.UPPER);
		
		courses = new ArrayList<Course>();
		courses.add(course);
	}

	@After
	public void tearDown() throws Exception {
		given().expect().statusCode(200).when().get("/course/resetTable");
	}

	@Test
	public void testCRUD() {

		// INSERT

		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().post("/course").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);

		assertEquals(1L, course.getId());
		assertEquals("Web Development", course.getName());
		assertEquals(Level.UPPER, course.getLevel());

		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().post("/course").andReturn().jsonPath();

		// GET BY ID

		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().get("/course/1").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);

		assertEquals(1L, course.getId());
		assertEquals("Web Development", course.getName());
		assertEquals(Level.UPPER, course.getLevel());
		
		// GET ALL
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(courses).expect()
				.statusCode(200).when().get("/course").andReturn().jsonPath();
		
		List<Course> resultado = retorno.getList("courses");
		assertEquals(courses.size(), resultado.size());

		// UPDATE

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

		// DELETE

		retorno = given().header("Accept", "application/json").contentType("application/json").body(course).expect()
				.statusCode(200).when().delete("/course/1").andReturn().jsonPath();

		course = retorno.getObject("course", Course.class);

		assertEquals(1L, course.getId());
		assertEquals(null, course.getName());
		assertEquals(null, course.getDescription());
		assertEquals(null, course.getJustification());
		
		// REPORT
		
		retorno = given().expect().statusCode(200).when().get("/course/report").andReturn().jsonPath();
		
		resultado = retorno.getList("courses");
		assertEquals(0, resultado.size());
	}

}
