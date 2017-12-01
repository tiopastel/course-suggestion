package tech.nerddash.coursesuggestion.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
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
import tech.nerddash.coursesuggestion.dao.DisciplineDao;
import tech.nerddash.coursesuggestion.model.Content;
import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;

@Controller
public class DisciplineController extends AbstractControllerClass<Discipline> {

	private final DisciplineDao dao;

	@Inject
	public DisciplineController(Environment environment, Validator validator, Result result, DisciplineDao dao) {
		super(environment, validator, result);
		this.dao = dao;
	}

	@Deprecated
	public DisciplineController() {
		this(null, null, null, null);
	}

	@Get("/discipline")
	public List<Discipline> list() {
		List<Discipline> disciplines = dao.listAll(Discipline.class);
		result.use(json()).from(disciplines, "disciplines").recursive().exclude("course.disciplines").exclude("contents.discipline").serialize();
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
	
	@Get("/discipline/resetTable")
	public void reset() {
		
		this.testing = Boolean.parseBoolean(environment.get("testing"));

		if (testing) {
			dao.resetTable(Discipline.class, Course.class);
			result.nothing();
		} else {
			result.notFound();
		}

	}
	
	@Get("/discipline/report")
	public List<Discipline> disciplineReport() {

		List<Discipline> disciplines = dao.listAll(Discipline.class);
		ArrayList<ReportData> reportDataArray = new ArrayList<ReportData>();
		
		for (Discipline discipline : disciplines) {

			ReportData reportData = new ReportData();
			reportData.disciplineName = discipline.getName();


				List<Content> contents = discipline.getContents();

				for (@SuppressWarnings("unused")
				Content content : contents) {
					reportData.contents++;
				}

			reportDataArray.add(reportData);
		}

		result.use(json()).from(reportDataArray, "disciplines").recursive().serialize();
		return disciplines;

	}

	private void useJson(Discipline discipline) {
		result.use(json()).from(discipline).recursive().exclude("course.disciplines").exclude("contents.discipline").serialize();
	}
	
	private class ReportData {

		@SuppressWarnings("unused")
		private String disciplineName;
		@SuppressWarnings("unused")
		private int contents;
	}

}
