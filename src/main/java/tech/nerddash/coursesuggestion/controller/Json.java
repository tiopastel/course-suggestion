package tech.nerddash.coursesuggestion.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import tech.nerddash.coursesuggestion.dao.CourseDao;
import tech.nerddash.coursesuggestion.model.Content;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;

@Controller
public class Json extends AbstractControllerClass<Course>{

	private Content content;
	private Discipline discipline;
	private List<Content> contents = new ArrayList<>();
	private List<Discipline> disciplines = new ArrayList<>();
	private Course course;


	@Inject
	public Json(Validator validator, Result result, CourseDao dao) {
		super(validator, result, dao);
	}
	
	public Json() {
		this(null, null, null);
	}

	@Get("/json")
	public void jason() {
		
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
		
		result.use(json()).from(course).recursive().serialize();
		

	}
}
