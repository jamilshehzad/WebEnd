package com.data.web;

import com.data.dao.CompanyDAO;

import com.data.domain.Company;

import com.data.service.CompanyService;

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
 * Spring MVC controller that handles CRUD requests for Company entities
 * 
 */

@Controller("CompanyController")
public class CompanyController {

	/**
	 * DAO injected by Spring that manages Company entities
	 * 
	 */
	@Autowired
	private CompanyDAO companyDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Company entities
	 * 
	 */
	@Autowired
	private CompanyService companyService;

	/**
	 */
	@RequestMapping("/companyController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
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
	 * Create a new Company entity
	 * 
	 */
	@RequestMapping("/newCompany")
	public ModelAndView newCompany() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("company", new Company());
		mav.addObject("newFlag", true);
		mav.setViewName("company/editCompany.jsp");

		return mav;
	}

	/**
	 * Select an existing Company entity
	 * 
	 */
	@RequestMapping("/selectCompany")
	public ModelAndView selectCompany(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(idKey));
		mav.setViewName("company/viewCompany.jsp");

		return mav;
	}

	/**
	 * Delete an existing Company entity
	 * 
	 */
	@RequestMapping("/deleteCompany")
	public String deleteCompany(@RequestParam Integer idKey) {
		Company company = companyDAO.findCompanyByPrimaryKey(idKey);
		companyService.deleteCompany(company);
		return "forward:/indexCompany";
	}

	/**
	 * Entry point to show all Company entities
	 * 
	 */
	public String indexCompany() {
		return "redirect:/indexCompany";
	}

	/**
	 * Show all Company entities
	 * 
	 */
	@RequestMapping("/indexCompany")
	public ModelAndView listCompanys() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("companys", companyService.loadCompanys());

		mav.setViewName("company/listCompanys.jsp");

		return mav;
	}

	/**
	 * Edit an existing Company entity
	 * 
	 */
	@RequestMapping("/editCompany")
	public ModelAndView editCompany(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(idKey));
		mav.setViewName("company/editCompany.jsp");

		return mav;
	}

	/**
	 * Save an existing Company entity
	 * 
	 */
	@RequestMapping("/saveCompany")
	public String saveCompany(@ModelAttribute Company company) {
		companyService.saveCompany(company);
		return "forward:/indexCompany";
	}

	/**
	 * Select the Company entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCompany")
	public ModelAndView confirmDeleteCompany(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(idKey));
		mav.setViewName("company/deleteCompany.jsp");

		return mav;
	}
}