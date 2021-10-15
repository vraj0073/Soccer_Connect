package com.csci5308project;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class Project5308ApplicationTests {

	@Test
	void contextLoads() {
	}

	Project5308Application projectInit = new Project5308Application();
	@org.junit.Test
	public void notNullTest() {
		assertNotNull(projectInit);
	}
}
