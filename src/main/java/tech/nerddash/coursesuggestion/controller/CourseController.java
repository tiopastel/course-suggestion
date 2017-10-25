package tech.nerddash.coursesuggestion.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import tech.nerddash.coursesuggestion.dao.CourseDao;
import tech.nerddash.coursesuggestion.model.Course;

@Controller
public class CourseController extends AbstractControllerClass<Course> {

	@Inject
	public CourseController(Validator validator, Result result, CourseDao dao) {
		super(validator, result, dao);
	}

	@Deprecated
	public CourseController() {
		this(null, null, null);
	}

	@Get({ "/course", "/course/" })
	public List<Course> list() {
		List<Course> courses = dao.listAll(Course.class);
		result.use(json()).from(courses).recursive().serialize();
		return courses;

	}

	@Get("/course/{course.id}")
	public Course get(Course course) {
		course = dao.get(Course.class, course.getId());
		result.use(json()).from(course).recursive().serialize();
		return course;
	}

	@Delete("/course/{course.id}")
	public boolean delete(Course course) {
		
		if (dao.delete(course)) {
			result.use(json()).from(course).recursive().serialize();
			return true;
		}
		return false;
	}

	@Consumes("application/json")
	@Post("/course")
	public Course insert(Course course) {

		validate(course);

		course = dao.insert(course);
		result.use(json()).from(course).recursive().serialize();
		return course;
	}

	@Consumes("application/json")
	@Put("/course")
	public Course update(Course course) {

		validate(course);
		course = dao.update(course);
		result.use(json()).from(course).recursive().serialize();
		return course;
	}

}
