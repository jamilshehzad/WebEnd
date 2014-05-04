package com.data.web;

import com.data.dao.PersonDAO;

import com.data.domain.Person;

import com.data.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Person entities
 * 
 */

@Controller("PersonController")
public class PersonController {

	/**
	 * DAO injected by Spring that manages Person entities
	 * 
	 */
	@Autowired
	private PersonDAO personDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Person entities
	 * 
	 */
	@Autowired
	private PersonService personService;

	/**
	 * Entry point to show all Person entities
	 * 
	 */
	public String indexPerson() {
		return "redirect:/indexPerson";
	}

	/**
	 * Save an existing Person entity
	 * 
	 */
	@RequestMapping("/savePerson")
	public String savePerson(@ModelAttribute Person person) {
		personService.savePerson(person);
		return "forward:/indexPerson";
	}

	/**
	 * Create a new Person entity
	 * 
	 */
	@RequestMapping("/newPerson")
	public ModelAndView newPerson() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("person", new Person());
		mav.addObject("newFlag", true);
		mav.setViewName("person/editPerson.jsp");

		return mav;
	}

	/**
	 * Select an existing Person entity
	 * 
	 */
	@RequestMapping("/selectPerson")
	public ModelAndView selectPerson(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("person", personDAO.findPersonByPrimaryKey(idKey));
		mav.setViewName("person/viewPerson.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/personController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Show all Person entities
	 * 
	 */
	@RequestMapping("/indexPerson")
	public ModelAndView listPersons() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("persons", personService.loadPersons());

		mav.setViewName("person/listPersons.jsp");

		return mav;
	}

	/**
	 * Edit an existing Person entity
	 * 
	 */
	@RequestMapping("/editPerson")
	public ModelAndView editPerson(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("person", personDAO.findPersonByPrimaryKey(idKey));
		mav.setViewName("person/editPerson.jsp");

		return mav;
	}

	/**
	 * Select the Person entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePerson")
	public ModelAndView confirmDeletePerson(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("person", personDAO.findPersonByPrimaryKey(idKey));
		mav.setViewName("person/deletePerson.jsp");

		return mav;
	}

	/**
	 * Register custom, context-specific property editors
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}

	/**
	 * Delete an existing Person entity
	 * 
	 */
	@RequestMapping("/deletePerson")
	public String deletePerson(@RequestParam Integer idKey) {
		Person person = personDAO.findPersonByPrimaryKey(idKey);
		personService.deletePerson(person);
		return "forward:/indexPerson";
	}
}