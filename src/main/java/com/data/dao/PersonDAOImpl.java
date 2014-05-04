package com.data.dao;

import com.data.domain.Person;

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
 * DAO to manage Person entities.
 * 
 */
@Repository("PersonDAO")
@Transactional
public class PersonDAOImpl extends AbstractJpaDao<Person> implements PersonDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Person.class }));

	/**
	 * EntityManager injected by Spring for persistence unit com_mysql_jdbc_Driver
	 *
	 */
	@PersistenceContext(unitName = "com_mysql_jdbc_Driver")
	private EntityManager entityManager;

	/**
	 * Instantiates a new PersonDAOImpl
	 *
	 */
	public PersonDAOImpl() {
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
	 * JPQL Query - findPersonByFunction
	 *
	 */
	@Transactional
	public Set<Person> findPersonByFunction(String function) throws DataAccessException {

		return findPersonByFunction(function, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByFunction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByFunction(String function, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByFunction", startResult, maxRows, function);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findPersonByLastName
	 *
	 */
	@Transactional
	public Set<Person> findPersonByLastName(String lastName) throws DataAccessException {

		return findPersonByLastName(lastName, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByLastName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByLastName(String lastName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByLastName", startResult, maxRows, lastName);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findPersonByLastNameContaining
	 *
	 */
	@Transactional
	public Set<Person> findPersonByLastNameContaining(String lastName) throws DataAccessException {

		return findPersonByLastNameContaining(lastName, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByLastNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByLastNameContaining(String lastName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByLastNameContaining", startResult, maxRows, lastName);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findPersonByBirthDateBefore
	 *
	 */
	@Transactional
	public Set<Person> findPersonByBirthDateBefore(java.util.Calendar birthDate) throws DataAccessException {

		return findPersonByBirthDateBefore(birthDate, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByBirthDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByBirthDateBefore(java.util.Calendar birthDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByBirthDateBefore", startResult, maxRows, birthDate);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findPersonByFirstName
	 *
	 */
	@Transactional
	public Set<Person> findPersonByFirstName(String firstName) throws DataAccessException {

		return findPersonByFirstName(firstName, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByFirstName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByFirstName(String firstName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByFirstName", startResult, maxRows, firstName);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findPersonById
	 *
	 */
	@Transactional
	public Person findPersonById(Integer id) throws DataAccessException {

		return findPersonById(id, -1, -1);
	}

	/**
	 * JPQL Query - findPersonById
	 *
	 */

	@Transactional
	public Person findPersonById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findPersonById", startResult, maxRows, id);
			return (com.data.domain.Person) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findPersonByFirstNameContaining
	 *
	 */
	@Transactional
	public Set<Person> findPersonByFirstNameContaining(String firstName) throws DataAccessException {

		return findPersonByFirstNameContaining(firstName, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByFirstNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByFirstNameContaining(String firstName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByFirstNameContaining", startResult, maxRows, firstName);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findPersonByBirthDate
	 *
	 */
	@Transactional
	public Set<Person> findPersonByBirthDate(java.util.Calendar birthDate) throws DataAccessException {

		return findPersonByBirthDate(birthDate, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByBirthDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByBirthDate(java.util.Calendar birthDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByBirthDate", startResult, maxRows, birthDate);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findPersonByBirthDateAfter
	 *
	 */
	@Transactional
	public Set<Person> findPersonByBirthDateAfter(java.util.Calendar birthDate) throws DataAccessException {

		return findPersonByBirthDateAfter(birthDate, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByBirthDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByBirthDateAfter(java.util.Calendar birthDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByBirthDateAfter", startResult, maxRows, birthDate);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findPersonByPrimaryKey
	 *
	 */
	@Transactional
	public Person findPersonByPrimaryKey(Integer id) throws DataAccessException {

		return findPersonByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByPrimaryKey
	 *
	 */

	@Transactional
	public Person findPersonByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findPersonByPrimaryKey", startResult, maxRows, id);
			return (com.data.domain.Person) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findPersonByFunctionContaining
	 *
	 */
	@Transactional
	public Set<Person> findPersonByFunctionContaining(String function) throws DataAccessException {

		return findPersonByFunctionContaining(function, -1, -1);
	}

	/**
	 * JPQL Query - findPersonByFunctionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findPersonByFunctionContaining(String function, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPersonByFunctionContaining", startResult, maxRows, function);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllPersons
	 *
	 */
	@Transactional
	public Set<Person> findAllPersons() throws DataAccessException {

		return findAllPersons(-1, -1);
	}

	/**
	 * JPQL Query - findAllPersons
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Person> findAllPersons(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllPersons", startResult, maxRows);
		return new LinkedHashSet<Person>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Person entity) {
		return true;
	}
}
