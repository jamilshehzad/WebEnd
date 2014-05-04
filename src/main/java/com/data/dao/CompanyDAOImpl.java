package com.data.dao;

import com.data.domain.Company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Company entities.
 * 
 */
@Repository("CompanyDAO")
@Transactional
public class CompanyDAOImpl extends AbstractJpaDao<Company> implements
		CompanyDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Company.class }));

	/**
	 * EntityManager injected by Spring for persistence unit com_mysql_jdbc_Driver
	 *
	 */
	@PersistenceContext(unitName = "com_mysql_jdbc_Driver")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CompanyDAOImpl
	 *
	 */
	public CompanyDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findCompanyByAddress
	 *
	 */
	@Transactional
	public Set<Company> findCompanyByAddress(String address) throws DataAccessException {

		return findCompanyByAddress(address, -1, -1);
	}

	/**
	 * JPQL Query - findCompanyByAddress
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByAddress(String address, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByAddress", startResult, maxRows, address);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	/**
	 * JPQL Query - findCompanyById
	 *
	 */
	@Transactional
	public Company findCompanyById(Integer id) throws DataAccessException {

		return findCompanyById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCompanyById
	 *
	 */

	@Transactional
	public Company findCompanyById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCompanyById", startResult, maxRows, id);
			return (com.data.domain.Company) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCompanyByPrimaryKey
	 *
	 */
	@Transactional
	public Company findCompanyByPrimaryKey(Integer id) throws DataAccessException {

		return findCompanyByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCompanyByPrimaryKey
	 *
	 */

	@Transactional
	public Company findCompanyByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCompanyByPrimaryKey", startResult, maxRows, id);
			return (com.data.domain.Company) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllCompanys
	 *
	 */
	@Transactional
	public Set<Company> findAllCompanys() throws DataAccessException {

		return findAllCompanys(-1, -1);
	}

	/**
	 * JPQL Query - findAllCompanys
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findAllCompanys(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCompanys", startResult, maxRows);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	/**
	 * JPQL Query - findCompanyByNameContaining
	 *
	 */
	@Transactional
	public Set<Company> findCompanyByNameContaining(String name) throws DataAccessException {

		return findCompanyByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findCompanyByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	/**
	 * JPQL Query - findCompanyByCreationDate
	 *
	 */
	@Transactional
	public Set<Company> findCompanyByCreationDate(java.util.Calendar creationDate) throws DataAccessException {

		return findCompanyByCreationDate(creationDate, -1, -1);
	}

	/**
	 * JPQL Query - findCompanyByCreationDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByCreationDate(java.util.Calendar creationDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByCreationDate", startResult, maxRows, creationDate);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	/**
	 * JPQL Query - findCompanyByName
	 *
	 */
	@Transactional
	public Set<Company> findCompanyByName(String name) throws DataAccessException {

		return findCompanyByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findCompanyByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByName", startResult, maxRows, name);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	/**
	 * JPQL Query - findCompanyByAddressContaining
	 *
	 */
	@Transactional
	public Set<Company> findCompanyByAddressContaining(String address) throws DataAccessException {

		return findCompanyByAddressContaining(address, -1, -1);
	}

	/**
	 * JPQL Query - findCompanyByAddressContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByAddressContaining(String address, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByAddressContaining", startResult, maxRows, address);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Company entity) {
		return true;
	}
}
