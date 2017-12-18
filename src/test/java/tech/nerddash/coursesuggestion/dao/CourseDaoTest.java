package tech.nerddash.coursesuggestion.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;

public class CourseDaoTest extends AbstractRepositoryTest {

	private CourseDao courseDao;
	private Course course;
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
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

	@Test
	public void testaListAll() {
		List<Course> courses = courseDao.listAll(Course.class);
		
		assertEquals("Não foi possível buscar os cursos", Arrays.asList(course), courses);
	}
	
	@Test
	public void testaMostVoted() {
		Course course = new Course();
		course.setName("Back-end Development");
		course.setDescription(
				"Back-end development is a broad term for the work involved in developing a web site for the Internet (World Wide Web) or an intranet (a private network). Web development can range from developing the simplest static single page of plain text to the most complex web-based internet applications (or just 'web apps') electronic businesses, and social network services. A more comprehensive list of tasks to which web development commonly refers, may include web engineering, web design, web content development, client liaison, client-side/server-side scripting, web server and network security configuration, and e-commerce development. Among web professionals, \"web development\" usually refers to the main non-design aspects of building web sites: writing markup and coding. Most recently Web development has come to mean the creation of content management systems or CMS.");
		course.setJustification("Becouse We can");
		course.setVotes(3L);
		
		courseDao.insert(course);
		
		List<Course> resultList = courseDao.mostVoted(Course.class);
		Course mostVotedCourse = resultList.get(0);
		long votesMostVoted = mostVotedCourse.getVotes();
		Course minusVotedCourse = resultList.get(resultList.size() - 1);
		long votesMinusVoted = minusVotedCourse.getVotes();
		
		assertThat(votesMostVoted, greaterThan(votesMinusVoted));
	}
}
