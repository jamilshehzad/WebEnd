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
 * Unit test for the <code>PersonController</code> controller.
 *
 * @see com.data.web.PersonController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./src/main/resources/WebEnd-security-context.xml",
		"file:./src/main/resources/WebEnd-service-context.xml",
		"file:./src/main/resources/WebEnd-dao-context.xml",
		"file:./src/main/resources/WebEnd-web-context.xml" })
public class PersonControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexPerson()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetindexPerson() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexPerson");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PersonController controller = (PersonController) context.getBean("PersonController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectPerson()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetselectPerson() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectPerson");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PersonController controller = (PersonController) context.getBean("PersonController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editPerson()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GeteditPerson() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editPerson");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PersonController controller = (PersonController) context.getBean("PersonController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>savePerson()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetsavePerson() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/savePerson");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PersonController controller = (PersonController) context.getBean("PersonController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newPerson()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetnewPerson() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newPerson");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PersonController controller = (PersonController) context.getBean("PersonController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeletePerson()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetconfirmDeletePerson() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeletePerson");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PersonController controller = (PersonController) context.getBean("PersonController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deletePerson()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetdeletePerson() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deletePerson");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PersonController controller = (PersonController) context.getBean("PersonController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>personControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void GetpersonControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/personController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PersonController controller = (PersonController) context.getBean("PersonController");

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