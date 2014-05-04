package com.data.service;

import com.data.dao.CompanyDAO;

import com.data.domain.Company;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Company entities
 * 
 */

@Service("CompanyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

	/**
	 * DAO injected by Spring that manages Company entities
	 * 
	 */
	@Autowired
	private CompanyDAO companyDAO;

	/**
	 * Instantiates a new CompanyServiceImpl.
	 *
	 */
	public CompanyServiceImpl() {
	}

	/**
	 * Load an existing Company entity
	 * 
	 */
	@Transactional
	public Set<Company> loadCompanys() {
		return companyDAO.findAllCompanys();
	}

	/**
	 * Return all Company entity
	 * 
	 */
	@Transactional
	public List<Company> findAllCompanys(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Company>(companyDAO.findAllCompanys(startResult, maxRows));
	}

	/**
	 */
	@Transactional
	public Company findCompanyByPrimaryKey(Integer id) {
		return companyDAO.findCompanyByPrimaryKey(id);
	}

	/**
	 * Delete an existing Company entity
	 * 
	 */
	@Transactional
	public void deleteCompany(Company company) {
		companyDAO.remove(company);
		companyDAO.flush();
	}

	/**
	 * Return a count of all Company entity
	 * 
	 */
	@Transactional
	public Integer countCompanys() {
		return ((Long) companyDAO.createQuerySingleResult("select count(o) from Company o").getSingleResult()).intValue();
	}

	/**
	 * Save an existing Company entity
	 * 
	 */
	@Transactional
	public void saveCompany(Company company) {
		Company existingCompany = companyDAO.findCompanyByPrimaryKey(company.getId());

		if (existingCompany != null) {
			if (existingCompany != company) {
				existingCompany.setId(company.getId());
				existingCompany.setName(company.getName());
				existingCompany.setAddress(company.getAddress());
				existingCompany.setCreationDate(company.getCreationDate());
			}
			company = companyDAO.store(existingCompany);
		} else {
			company = companyDAO.store(company);
		}
		companyDAO.flush();
	}
}
