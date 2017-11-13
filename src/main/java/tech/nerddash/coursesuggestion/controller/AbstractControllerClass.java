package tech.nerddash.coursesuggestion.controller;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.Validator;
import tech.nerddash.coursesuggestion.model.AbstractEntityClass;

public abstract class AbstractControllerClass<E extends AbstractEntityClass> {

	protected final Validator validator;
	protected final Result result;
	protected final String ERROR_TAG = "API-Error";
	protected final Environment environment;
	protected boolean testing = false;

	@Inject
	public AbstractControllerClass(Environment environment, Validator validator, Result result) {
		this.environment = environment;
		this.validator = validator;
		this.result = result;
	}

	protected void validate(E entityClass) {
		validator.validate(entityClass);
		validator.onErrorUse(json()).from(validator.getErrors(), ERROR_TAG).serialize();
	}

}
