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
import tech.nerddash.coursesuggestion.dao.DisciplineDao;
import tech.nerddash.coursesuggestion.model.Discipline;

@Controller
public class DisciplineController extends AbstractControllerClass<Discipline> {

	private final DisciplineDao dao;

	@Inject
	public DisciplineController(Validator validator, Result result, DisciplineDao dao) {
		super(validator, result);
		this.dao = dao;
	}

	@Deprecated
	public DisciplineController() {
		this(null, null, null);
	}

	@Get({ "/discipline", "/discipline/" })
	public List<Discipline> list() {
		List<Discipline> disciplines = dao.listAll(Discipline.class);
		result.use(json()).from(disciplines).exclude("course.disciplines").serialize();
		return disciplines;

	}

	@Get("/discipline/{discipline.id}")
	public Discipline get(Discipline discipline) {
		discipline = dao.get(Discipline.class, discipline.getId());
		useJson(discipline);
		return discipline;
	}

	@Delete("/discipline/{discipline.id}")
	public boolean delete(Discipline discipline) {

		if (dao.delete(discipline)) {
			useJson(discipline);
			return true;
		}
		return false;
	}

	@Consumes("application/json")
	@Post("/discipline")
	public Discipline insert(Discipline discipline) {

		validate(discipline);
		
		discipline = dao.insert(discipline);
		useJson(discipline);
		return discipline;
	}

	@Consumes("application/json")
	@Put("/discipline")
	public Discipline update(Discipline discipline) {

		validate(discipline);
		discipline = dao.update(discipline);
		useJson(discipline);
		return discipline;
	}

	private void useJson(Discipline discipline) {
		result.use(json()).from(discipline).recursive().exclude("course.disciplines").exclude("contents.discipline").serialize();
	}

}
