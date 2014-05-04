package com.data.service;

import com.data.domain.Person;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

/**
 * Class to run the service as a JUnit test. Each operation in the service is a separate test.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
		"file:./src/main/resources/WebEnd-security-context.xml",
		"file:./src/main/resources/WebEnd-service-context.xml",
		"file:./src/main/resources/WebEnd-dao-context.xml",
		"file:./src/main/resources/WebEnd-web-context.xml" })
@Transactional
public class PersonServiceTest {

	/**
	 * The Spring application context.
	 *
	 */
	@SuppressWarnings("unused")
	private ApplicationContext context;

	/**
	 * The service being tested, injected by Spring.
	 *
	 */
	@Autowired
	protected PersonService service;

	/**
	 * Instantiates a new PersonServiceTest.
	 *
	 */
	public PersonServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Person entity
	 * 
	 */
	@Test
	public void countPersons() {
		Integer response = null;
		response = service.countPersons();
		// TODO: JUnit - Add assertions to test outputs of operation: countPersons
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findPersonByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findPersonByPrimaryKey 
		Integer id = 0;
		Person response = null;
		response = service.findPersonByPrimaryKey(id);
		// TODO: JUnit - Add assertions to test outputs of operation: findPersonByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Save an existing Person entity
	 * 
	 */
	@Test
	public void savePerson() {
		// TODO: JUnit - Populate test inputs for operation: savePerson 
		Person person = new com.data.domain.Person();
		service.savePerson(person);
	}

	/**
	 * Operation Unit Test
	 * Load an existing Person entity
	 * 
	 */
	@Test
	public void loadPersons() {
		Set<Person> response = null;
		response = service.loadPersons();
		// TODO: JUnit - Add assertions to test outputs of operation: loadPersons
	}

	/**
	 * Operation Unit Test
	 * Return all Person entity
	 * 
	 */
	@Test
	public void findAllPersons() {
		// TODO: JUnit - Populate test inputs for operation: findAllPersons 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Person> response = null;
		response = service.findAllPersons(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllPersons
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Person entity
	 * 
	 */
	@Test
	public void deletePerson() {
		// TODO: JUnit - Populate test inputs for operation: deletePerson 
		Person person_1 = new com.data.domain.Person();
		service.deletePerson(person_1);
	}

	/**
	 * Autowired to set the Spring application context.
	 *
	 */
	@Autowired
	public void setContext(ApplicationContext context) {
		this.context = context;
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("session", new SessionScope());
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("request", new RequestScope());
	}

	/**
	 * Sets Up the Request context
	 *
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
	}
}
