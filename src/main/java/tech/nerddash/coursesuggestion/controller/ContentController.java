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
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.Validator;
import tech.nerddash.coursesuggestion.dao.ContentDao;
import tech.nerddash.coursesuggestion.model.Content;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;

@Controller
public class ContentController extends AbstractControllerClass<Content> {

	private final ContentDao dao;

	@Inject
	public ContentController(Environment environment, Validator validator, Result result, ContentDao dao) {
		super(environment, validator, result);
		this.dao = dao;
	}

	@Deprecated
	public ContentController() {
		this(null, null, null, null);
	}

	@Get({ "/content", "/content/" })
	public List<Content> list() {
		List<Content> contents = dao.listAll(Content.class);
		result.use(json()).from(contents, "contents").recursive().exclude("discipline.contents")
				.exclude("discipline.course.disciplines").serialize();
		return contents;

	}

	@Get("/content/{content.id}")
	public Content get(Content content) {
		content = dao.get(Content.class, content.getId());
		useJson(content);
		return content;
	}

	@Delete("/content/{content.id}")
	public boolean delete(Content content) {

		if (dao.delete(content)) {
			useJson(content);
			return true;
		}
		return false;
	}

	@Consumes("application/json")
	@Post("/content")
	public Content insert(Content content) {

		validate(content);

		content = dao.insert(content);
		useJson(content);
		return content;
	}

	@Consumes("application/json")
	@Put("/content")
	public Content update(Content content) {

		validate(content);
		content = dao.update(content);
		useJson(content);
		return content;
	}

	@Get("/content/resetTable")
	public void reset() {

		this.testing = Boolean.parseBoolean(environment.get("testing"));

		if (testing) {
			dao.resetTable(Content.class, Discipline.class, Course.class);
			result.nothing();
		} else {
			result.notFound();
		}

	}

	private void useJson(Content content) {
		result.use(json()).from(content).recursive().exclude("discipline.contents")
				.exclude("discipline.course.disciplines").serialize();
	}

}
