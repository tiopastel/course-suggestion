package tech.nerddash.coursesuggestion.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;



public class DisciplineDaoTest extends AbstractRepositoryTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	private CourseDao courseDao;
	private Course course;
	
	private DisciplineDao disciplineDao;
	private Discipline discipline;

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
		
		discipline = new Discipline();
		discipline.setName("Web Development");
		discipline.setDescription(
				"Web development is a broad term for the work involved in developing a web site for the Internet (World Wide Web) or an intranet (a private network). Web development can range from developing the simplest static single page of plain text to the most complex web-based internet applications (or just 'web apps') electronic businesses, and social network services. A more comprehensive list of tasks to which web development commonly refers, may include web engineering, web design, web content development, client liaison, client-side/server-side scripting, web server and network security configuration, and e-commerce development. Among web professionals, \"web development\" usually refers to the main non-design aspects of building web sites: writing markup and coding. Most recently Web development has come to mean the creation of content management systems or CMS.");
		discipline.setJustification("Becouse We can");
		
		discipline.setCourse(course);

		disciplineDao = new DisciplineDao(em);
		
		/*
		 * Insere ao início de cada testes
		 */

		disciplineDao.insert(discipline);
		
		entityObject = discipline;
		
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		resetTable(course);

	}

	@Test
	public void testaInsert() {
		assertEquals("Não foi inserido", "Web Development", disciplineDao.get(Discipline.class, 1L).getName());

	}
	
	@Test
	public void testaUpdate() {
		
		discipline.setName("Database Development");
		
		discipline = disciplineDao.update(discipline);
		
		assertEquals("Não pode ser atualizado", "Database Development", disciplineDao.get(Discipline.class, 1L).getName());

	}
	
	@Test
	public void testaGet() {
		
		assertEquals("Não foi possível buscar.", "Web Development", disciplineDao.get(Discipline.class, 1L).getName());

	}
	
	@Test
	public void testaDelete() {
		
		
		disciplineDao.delete(discipline);
		
		assertEquals("Não pode ser apagado", null, disciplineDao.get(Discipline.class, 1L));

	}

}
