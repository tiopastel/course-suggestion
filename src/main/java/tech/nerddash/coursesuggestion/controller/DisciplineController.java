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
	
	@Inject
	public DisciplineController(Validator validator, Result result, DisciplineDao dao) {
		super(validator, result, dao);
	}

	@Deprecated
	public DisciplineController() {
		this(null, null, null);
	}

	@Get({ "/discipline", "/discipline/" })
	public List<Discipline> list() {
		List<Discipline> disciplines = dao.listAll(Discipline.class);
		result.use(json()).from(disciplines).recursive().serialize();
		return disciplines;

	}

	@Get("/discipline/{discipline.id}")
	public Discipline get(Discipline discipline) {
		discipline = dao.get(Discipline.class, discipline.getId());
		result.use(json()).from(discipline).recursive().serialize();
		return discipline;
	}

	@Delete("/discipline/{discipline.id}")
	public boolean delete(Discipline discipline) {
		
		if (dao.delete(discipline)) {
			result.use(json()).from(discipline).recursive().serialize();
			return true;
		}
		return false;
	}

	@Consumes("application/json")
	@Post("/discipline")
	public Discipline insert(Discipline discipline) {

		validate(discipline);

		discipline = dao.insert(discipline);
		result.use(json()).from(discipline).recursive().serialize();
		return discipline;
	}

	@Consumes("application/json")
	@Put("/discipline")
	public Discipline update(Discipline discipline) {

		validate(discipline);
		discipline = dao.update(discipline);
		result.use(json()).from(discipline).recursive().serialize();
		return discipline;
	}

}
