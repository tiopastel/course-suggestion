package tech.nerddash.coursesuggestion.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import tech.nerddash.coursesuggestion.model.Course;

@RequestScoped
public class CourseDao extends AbstractDaoClass<Course>{

	@Inject
	public CourseDao(EntityManager em) {
		super(em);
	}

	@Deprecated
	public CourseDao() {
		this(null);
	}

}
