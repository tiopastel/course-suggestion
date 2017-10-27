package tech.nerddash.coursesuggestion.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import tech.nerddash.coursesuggestion.model.Course;
import tech.nerddash.coursesuggestion.model.Discipline;

@RequestScoped
public class DisciplineDao extends AbstractDaoClass<Discipline> {

	private final CourseDao courseDao;

	@Inject
	public DisciplineDao(EntityManager em, CourseDao courseDao) {
		super(em);
		this.courseDao = courseDao;
	}

	@Deprecated
	public DisciplineDao() {
		this(null, null);
	}

	@Override
	public Discipline insert(Discipline discipline) {

		Course course = courseDao.get(Course.class, discipline.getCourse().getId());

		if (course != null) {

			discipline.setCourse(course);
			this.em.persist(discipline);
			em.refresh(discipline);
			return discipline;
		}
		return null;
	}
}
