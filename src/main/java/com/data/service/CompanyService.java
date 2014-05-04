package com.data.service;

import com.data.domain.Company;

import java.util.List;
import java.util.Set;

/**
 * Spring service that handles CRUD requests for Company entities
 * 
 */
public interface CompanyService {

	/**
	 * Load an existing Company entity
	 * 
	 */
	public Set<Company> loadCompanys();

	/**
	 * Return all Company entity
	 * 
	 */
	public List<Company> findAllCompanys(Integer startResult, Integer maxRows);

	/**
	 */
	public Company findCompanyByPrimaryKey(Integer id);

	/**
	 * Delete an existing Company entity
	 * 
	 */
	public void deleteCompany(Company company);

	/**
	 * Return a count of all Company entity
	 * 
	 */
	public Integer countCompanys();

	/**
	 * Save an existing Company entity
	 * 
	 */
	public void saveCompany(Company company_1);
}