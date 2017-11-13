package tech.nerddash.coursesuggestion.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import tech.nerddash.coursesuggestion.model.Course;

public class CourseDaoTest extends AbstractRepositoryTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	private CourseDao courseDao;
	private Course course;

	@Before
	public void setUp() throws Exception {
		
		course = new Course();
		course.setName("Web Development");
		course.setDescription(
				"Web development is a broad term for the work involved in developing a web site for the Internet (World Wide Web) or an intranet (a private network). Web development can range from developing the simplest static single page of plain text to the most complex web-based internet applications (or just 'web apps') electronic businesses, and social network services. A more comprehensive list of tasks to which web development commonly refers, may include web engineering, web design, web content development, client liaison, client-side/server-side scripting, web server and network security configuration, and e-commerce development. Among web professionals, \"web development\" usually refers to the main non-design aspects of building web sites: writing markup and coding. Most recently Web development has come to mean the creation of content management systems or CMS.");
		course.setJustification("Becouse We can");

		courseDao = new CourseDao(em);
		
		/*
		 * Insere ao início de cada testes
		 */

		courseDao.insert(course);
		
	}
	
	@After
	public void tearDown() throws Exception {
		courseDao.resetTable(Course.class);
	}
	


	@Test
	public void testaInsert() {
		assertEquals("Não foi inserido", "Web Development", courseDao.get(Course.class, 1L).getName());

	}
	
	@Test
	public void testaUpdate() {
		
		course.setName("Database Development");
		
		course = courseDao.update(course);
		
		assertEquals("Não pode ser atualizado", "Database Development", courseDao.get(Course.class, 1L).getName());

	}
	
	@Test
	public void testaGet() {
		
		assertEquals("Não foi possível buscar.", "Web Development", courseDao.get(Course.class, 1L).getName());

	}
	
	@Test
	public void testaDelete() {
		
		
		courseDao.delete(course);
		
		assertEquals("Não pode ser apagado", null, courseDao.get(Course.class, 1L));

	}

}
