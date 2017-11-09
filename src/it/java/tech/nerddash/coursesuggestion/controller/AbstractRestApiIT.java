package tech.nerddash.coursesuggestion.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Table;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import tech.nerddash.coursesuggestion.model.AbstractEntityClass;

public abstract class AbstractRestApiIT {
	
	protected static EntityManagerFactory emFactory;
	protected static EntityManager em;
	protected AbstractEntityClass entityObject;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		RestAssured.baseURI = "http://tomcat8:8080/tech.nerddash";
		
	}

	protected String getColumnName(AbstractEntityClass entityClass) {
		return ((Table) entityClass.getClass().getAnnotation(Table.class)).name();
	}


}
