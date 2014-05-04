package com.data.dao;

import com.data.domain.Company;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Company entities.
 * 
 */
public interface CompanyDAO extends JpaDao<Company> {

	/**
	 * JPQL Query - findCompanyByAddress
	 *
	 */
	public Set<Company> findCompanyByAddress(String address) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByAddress
	 *
	 */
	public Set<Company> findCompanyByAddress(String address, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyById
	 *
	 */
	public Company findCompanyById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyById
	 *
	 */
	public Company findCompanyById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByPrimaryKey
	 *
	 */
	public Company findCompanyByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByPrimaryKey
	 *
	 */
	public Company findCompanyByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCompanys
	 *
	 */
	public Set<Company> findAllCompanys() throws DataAccessException;

	/**
	 * JPQL Query - findAllCompanys
	 *
	 */
	public Set<Company> findAllCompanys(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByNameContaining
	 *
	 */
	public Set<Company> findCompanyByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByNameContaining
	 *
	 */
	public Set<Company> findCompanyByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByCreationDate
	 *
	 */
	public Set<Company> findCompanyByCreationDate(java.util.Calendar creationDate) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByCreationDate
	 *
	 */
	public Set<Company> findCompanyByCreationDate(Calendar creationDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByName
	 *
	 */
	public Set<Company> findCompanyByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByName
	 *
	 */
	public Set<Company> findCompanyByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByAddressContaining
	 *
	 */
	public Set<Company> findCompanyByAddressContaining(String address_1) throws DataAccessException;

	/**
	 * JPQL Query - findCompanyByAddressContaining
	 *
	 */
	public Set<Company> findCompanyByAddressContaining(String address_1, int startResult, int maxRows) throws DataAccessException;

}