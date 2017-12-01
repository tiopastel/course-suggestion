package tech.nerddash.coursesuggestion.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.Validator;
import tech.nerddash.coursesuggestion.dao.CourseDao;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Course.Level;

@Controller
public class CourseController extends AbstractControllerClass<Course> {

	private final CourseDao dao;

	@Inject
	public CourseController(Environment environment, Validator validator, Result result, CourseDao dao) {
		super(environment, validator, result);
		this.dao = dao;
	}

	@Deprecated
	public CourseController() {
		this(null, null, null, null);
	}

	@Get({ "/course", "/course/" })
	public List<Course> list() {
		List<Course> courses = dao.listAll(Course.class);
		result.use(json()).from(courses).recursive().exclude("disciplines.course")
				.exclude("disciplines.contents.discipline").serialize();
		return courses;

	}

	@Get("/course/{course.id}")
	public Course get(Course course) {
		course = dao.get(Course.class, course.getId());
		useJson(course);
		return course;
	}

	@Delete("/course/{course.id}")
	public boolean delete(Course course) {

		if (dao.delete(course)) {
			useJson(course);
			return true;
		}
		return false;
	}

	@Consumes("application/json")
	@Post("/course")
	public Course insert(Course course) {

		validate(course);

		course = dao.insert(course);
		useJson(course);
		return course;
	}

	@Consumes("application/json")
	@Put("/course")
	public Course update(Course course) {

		validate(course);
		course = dao.update(course);
		useJson(course);
		return course;
	}

	@Get("/course/resetTable")
	public void reset() {

		this.testing = Boolean.parseBoolean(environment.get("testing"));

		if (testing) {
			dao.resetTable(Course.class);
			result.nothing();
		} else {
			result.notFound();
		}

	}

	@Get("/course/levels")
	public void levels() {

		Level[] levels = Course.Level.values();
		HashMap<String, String> hashMap = new HashMap<String, String>();

		for (Level level : levels) {
			hashMap.put(level.toString(), environment.get(level.toString().toLowerCase()));
		}

		result.use(json()).from(hashMap, "levels").recursive().serialize();
	}

	private void useJson(Course course) {
		result.use(json()).from(course).recursive().exclude("disciplines.course")
				.exclude("disciplines.contents.discipline").serialize();
	}

}
