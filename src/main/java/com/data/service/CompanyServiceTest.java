package com.data.service;

import com.data.domain.Company;

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
public class CompanyServiceTest {

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
	protected CompanyService service;

	/**
	 * Instantiates a new CompanyServiceTest.
	 *
	 */
	public CompanyServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Load an existing Company entity
	 * 
	 */
	@Test
	public void loadCompanys() {
		Set<Company> response = null;
		response = service.loadCompanys();
		// TODO: JUnit - Add assertions to test outputs of operation: loadCompanys
	}

	/**
	 * Operation Unit Test
	 * Return all Company entity
	 * 
	 */
	@Test
	public void findAllCompanys() {
		// TODO: JUnit - Populate test inputs for operation: findAllCompanys 
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Company> response = null;
		response = service.findAllCompanys(startResult, maxRows);
		// TODO: JUnit - Add assertions to test outputs of operation: findAllCompanys
	}

	/**
	 * Operation Unit Test
	 */
	@Test
	public void findCompanyByPrimaryKey() {
		// TODO: JUnit - Populate test inputs for operation: findCompanyByPrimaryKey 
		Integer id = 0;
		Company response = null;
		response = service.findCompanyByPrimaryKey(id);
		// TODO: JUnit - Add assertions to test outputs of operation: findCompanyByPrimaryKey
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Company entity
	 * 
	 */
	@Test
	public void deleteCompany() {
		// TODO: JUnit - Populate test inputs for operation: deleteCompany 
		Company company = new com.data.domain.Company();
		service.deleteCompany(company);
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Company entity
	 * 
	 */
	@Test
	public void countCompanys() {
		Integer response = null;
		response = service.countCompanys();
		// TODO: JUnit - Add assertions to test outputs of operation: countCompanys
	}

	/**
	 * Operation Unit Test
	 * Save an existing Company entity
	 * 
	 */
	@Test
	public void saveCompany() {
		// TODO: JUnit - Populate test inputs for operation: saveCompany 
		Company company_1 = new com.data.domain.Company();
		service.saveCompany(company_1);
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
