package com.data.web;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

/**
 * Unit test for the <code>CompanyController</code> controller.
 *
 * @see com.data.web.CompanyController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./src/main/resources/WebEnd-security-context.xml",
		"file:./src/main/resources/WebEnd-service-context.xml",
		"file:./src/main/resources/WebEnd-dao-context.xml",
		"file:./src/main/resources/WebEnd-web-context.xml" })
public class CompanyControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexCompany()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexCompany() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexCompany");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		CompanyController controller = (CompanyController) context.getBean("CompanyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectCompany()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectCompany() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectCompany");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		CompanyController controller = (CompanyController) context.getBean("CompanyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editCompany()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditCompany() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editCompany");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		CompanyController controller = (CompanyController) context.getBean("CompanyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveCompany()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsaveCompany() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveCompany");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		CompanyController controller = (CompanyController) context.getBean("CompanyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newCompany()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewCompany() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newCompany");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		CompanyController controller = (CompanyController) context.getBean("CompanyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteCompany()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeleteCompany() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteCompany");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		CompanyController controller = (CompanyController) context.getBean("CompanyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteCompany()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeleteCompany() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteCompany");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		CompanyController controller = (CompanyController) context.getBean("CompanyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>companyControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetcompanyControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/companyController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		CompanyController controller = (CompanyController) context.getBean("CompanyController");

		// TODO Invoke method and Assert return values

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
	 * Returns a mock HttpServletRequest object.
	 *
	 */
	private MockHttpServletRequest getMockHttpServletRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
		return request;
	}

	/**
	 * Returns a mock HttpServletResponse object.
	 *
	 */
	private MockHttpServletResponse getMockHttpServletResponse() {
		return new MockHttpServletResponse();
	}
}