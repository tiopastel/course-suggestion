package tech.nerddash.coursesuggestion.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import tech.nerddash.coursesuggestion.model.Discipline;

@RequestScoped
public class DisciplineDao extends AbstractDaoClass<Discipline> {


	@Inject
	public DisciplineDao(EntityManager em) {
		super(em);
	}

	@Deprecated
	public DisciplineDao() {
		this(null);
	}

}
