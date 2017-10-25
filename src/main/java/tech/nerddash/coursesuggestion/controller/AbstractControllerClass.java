package tech.nerddash.coursesuggestion.controller;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import tech.nerddash.coursesuggestion.dao.AbstractDaoClass;
import tech.nerddash.coursesuggestion.model.AbstractEntityClass;


public abstract class AbstractControllerClass<E extends AbstractEntityClass> {


	protected final Validator validator;	
	protected final AbstractDaoClass<E> dao;
	protected final Result result;
	protected final String ERROR_TAG = "API-Error";


	@Inject
	public AbstractControllerClass(Validator validator, Result result, AbstractDaoClass<E> dao) {
		this.validator = validator;
		this.result = result;
		this.dao = dao;
	}

	
	protected void validate(E entityClass) {
		validator.validate(entityClass);
		validator.onErrorUse(json()).from(validator.getErrors(), ERROR_TAG).serialize();
	}

}
