package com.data.dao;

import com.data.domain.Person;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Person entities.
 * 
 */
public interface PersonDAO extends JpaDao<Person> {

	/**
	 * JPQL Query - findPersonByFunction
	 *
	 */
	public Set<Person> findPersonByFunction(String function) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByFunction
	 *
	 */
	public Set<Person> findPersonByFunction(String function, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByLastName
	 *
	 */
	public Set<Person> findPersonByLastName(String lastName) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByLastName
	 *
	 */
	public Set<Person> findPersonByLastName(String lastName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByLastNameContaining
	 *
	 */
	public Set<Person> findPersonByLastNameContaining(String lastName_1) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByLastNameContaining
	 *
	 */
	public Set<Person> findPersonByLastNameContaining(String lastName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByBirthDateBefore
	 *
	 */
	public Set<Person> findPersonByBirthDateBefore(java.util.Calendar birthDate) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByBirthDateBefore
	 *
	 */
	public Set<Person> findPersonByBirthDateBefore(Calendar birthDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByFirstName
	 *
	 */
	public Set<Person> findPersonByFirstName(String firstName) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByFirstName
	 *
	 */
	public Set<Person> findPersonByFirstName(String firstName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonById
	 *
	 */
	public Person findPersonById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findPersonById
	 *
	 */
	public Person findPersonById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByFirstNameContaining
	 *
	 */
	public Set<Person> findPersonByFirstNameContaining(String firstName_1) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByFirstNameContaining
	 *
	 */
	public Set<Person> findPersonByFirstNameContaining(String firstName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByBirthDate
	 *
	 */
	public Set<Person> findPersonByBirthDate(java.util.Calendar birthDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByBirthDate
	 *
	 */
	public Set<Person> findPersonByBirthDate(Calendar birthDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByBirthDateAfter
	 *
	 */
	public Set<Person> findPersonByBirthDateAfter(java.util.Calendar birthDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByBirthDateAfter
	 *
	 */
	public Set<Person> findPersonByBirthDateAfter(Calendar birthDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByPrimaryKey
	 *
	 */
	public Person findPersonByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByPrimaryKey
	 *
	 */
	public Person findPersonByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByFunctionContaining
	 *
	 */
	public Set<Person> findPersonByFunctionContaining(String function_1) throws DataAccessException;

	/**
	 * JPQL Query - findPersonByFunctionContaining
	 *
	 */
	public Set<Person> findPersonByFunctionContaining(String function_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllPersons
	 *
	 */
	public Set<Person> findAllPersons() throws DataAccessException;

	/**
	 * JPQL Query - findAllPersons
	 *
	 */
	public Set<Person> findAllPersons(int startResult, int maxRows) throws DataAccessException;

}