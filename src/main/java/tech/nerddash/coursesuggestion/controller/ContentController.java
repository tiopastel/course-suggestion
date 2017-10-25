package tech.nerddash.coursesuggestion.controller;


import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import tech.nerddash.coursesuggestion.dao.ContentDao;
import tech.nerddash.coursesuggestion.model.Content;

public class ContentController extends AbstractControllerClass<Content>{
	@Inject
	public ContentController(Validator validator, Result result, ContentDao dao) {
		super(validator, result, dao);
	}

	@Deprecated
	public ContentController() {
		this(null, null, null);
	}

	@Get({ "/content", "/content/" })
	public List<Content> list() {
		List<Content> contents = dao.listAll(Content.class);
		result.use(json()).from(contents).recursive().serialize();
		return contents;

	}

	@Get("/content/{content.id}")
	public Content get(Content content) {
		content = dao.get(Content.class, content.getId());
		result.use(json()).from(content).recursive().serialize();
		return content;
	}

	@Delete("/content/{content.id}")
	public boolean delete(Content content) {
		
		if (dao.delete(content)) {
			result.use(json()).from(content).recursive().serialize();
			return true;
		}
		return false;
	}

	@Consumes("application/json")
	@Post("/content")
	public Content insert(Content content) {

		validate(content);

		content = dao.insert(content);
		result.use(json()).from(content).recursive().serialize();
		return content;
	}

	@Consumes("application/json")
	@Put("/content")
	public Content update(Content content) {

		validate(content);
		content = dao.update(content);
		result.use(json()).from(content).recursive().serialize();
		return content;
	}

}
