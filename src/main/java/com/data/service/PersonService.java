package com.data.service;

import com.data.domain.Person;

import java.util.List;
import java.util.Set;

/**
 * Spring service that handles CRUD requests for Person entities
 * 
 */
public interface PersonService {

	/**
	 * Return a count of all Person entity
	 * 
	 */
	public Integer countPersons();

	/**
	 */
	public Person findPersonByPrimaryKey(Integer id);

	/**
	 * Save an existing Person entity
	 * 
	 */
	public void savePerson(Person person);

	/**
	 * Load an existing Person entity
	 * 
	 */
	public Set<Person> loadPersons();

	/**
	 * Return all Person entity
	 * 
	 */
	public List<Person> findAllPersons(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing Person entity
	 * 
	 */
	public void deletePerson(Person person_1);
}