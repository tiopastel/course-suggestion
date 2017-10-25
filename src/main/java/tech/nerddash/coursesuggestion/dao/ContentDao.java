package tech.nerddash.coursesuggestion.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import tech.nerddash.coursesuggestion.model.Content;

@RequestScoped
public class ContentDao extends AbstractDaoClass<Content> {

	@Inject
	public ContentDao(EntityManager em) {
		super(em);
	}

	@Deprecated
	public ContentDao() {
		this(null);
	}

}
