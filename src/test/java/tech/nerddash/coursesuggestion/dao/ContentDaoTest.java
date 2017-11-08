package tech.nerddash.coursesuggestion.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import tech.nerddash.coursesuggestion.model.Content;



public class ContentDaoTest extends AbstractRepositoryTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	private ContentDao contentDao;
	private Content content;

	@Before
	public void setUp() throws Exception {
		
		content = new Content();
		content.setName("Web Development");
		content.setDescription(
				"Web development is a broad term for the work involved in developing a web site for the Internet (World Wide Web) or an intranet (a private network). Web development can range from developing the simplest static single page of plain text to the most complex web-based internet applications (or just 'web apps') electronic businesses, and social network services. A more comprehensive list of tasks to which web development commonly refers, may include web engineering, web design, web content development, client liaison, client-side/server-side scripting, web server and network security configuration, and e-commerce development. Among web professionals, \"web development\" usually refers to the main non-design aspects of building web sites: writing markup and coding. Most recently Web development has come to mean the creation of content management systems or CMS.");
		content.setJustification("Becouse We can");

		contentDao = new ContentDao(em);
		
		/*
		 * Insere ao início de cada testes
		 */

		contentDao.insert(content);
		
		entityObject = content;
		
	}

	@Test
	public void testaInsert() {
		assertEquals("Não foi inserido", "Web Development", contentDao.get(Content.class, 1L).getName());

	}
	
	@Test
	public void testaUpdate() {
		
		content.setName("Database Development");
		
		content = contentDao.update(content);
		
		assertEquals("Não pode ser atualizado", "Database Development", contentDao.get(Content.class, 1L).getName());

	}
	
	@Test
	public void testaGet() {
		
		assertEquals("Não foi possível buscar.", "Web Development", contentDao.get(Content.class, 1L).getName());

	}
	
	@Test
	public void testaDelete() {
		
		
		contentDao.delete(content);
		
		assertEquals("Não pode ser apagado", null, contentDao.get(Content.class, 1L));

	}

}
