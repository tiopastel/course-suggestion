package tech.nerddash.coursesuggestion.controller;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public abstract class AbstractRestApiIT {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		RestAssured.baseURI = "http://localhost:8888/tech.nerddash";

	}

}
