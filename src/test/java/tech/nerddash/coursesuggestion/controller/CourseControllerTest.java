package tech.nerddash.coursesuggestion.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
	private Course course;
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {
		
		content = new Content();
		discipline = new Discipline();
		course = new Course();

		content.setName("Docker");
		content.setDescription(" Docker provides an additional layer of abstraction and automation of operating-system-level virtualization on Windows and Linux.");
		content.setJustification("Docker is a tecnologie used in many applications in the actual development cenario.");
		content.setDiscipline(discipline);
		
		discipline.setName("Server Implementation");
		discipline.setDescription("In this discipline students lear how to implements servers");
		discipline.setJustification("Helps students to understand how and way to implements servers");
		
		course.setName("Web Development");
		
		course.setDescription(
				"Web development is a broad term for the work involved in developing a web site for the Internet (World Wide Web) or an intranet (a private network). Web development can range from developing the simplest static single page of plain text to the most complex web-based internet applications (or just 'web apps') electronic businesses, and social network services. A more comprehensive list of tasks to which web development commonly refers, may include web engineering, web design, web content development, client liaison, client-side/server-side scripting, web server and network security configuration, and e-commerce development. Among web professionals, \"web development\" usually refers to the main non-design aspects of building web sites: writing markup and coding. Most recently Web development has come to mean the creation of content management systems or CMS.");
		course.setJustification("Becouse We can");
		
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
