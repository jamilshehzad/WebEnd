package com.data.service;

import com.data.dao.PersonDAO;

import com.data.domain.Person;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Person entities
 * 
 */

@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

	/**
	 * DAO injected by Spring that manages Person entities
	 * 
	 */
	@Autowired
	private PersonDAO personDAO;

	/**
	 * Instantiates a new PersonServiceImpl.
	 *
	 */
	public PersonServiceImpl() {
	}

	/**
	 * Return a count of all Person entity
	 * 
	 */
	@Transactional
	public Integer countPersons() {
		return ((Long) personDAO.createQuerySingleResult("select count(o) from Person o").getSingleResult()).intValue();
	}

	/**
	 */
	@Transactional
	public Person findPersonByPrimaryKey(Integer id) {
		return personDAO.findPersonByPrimaryKey(id);
	}

	/**
	 * Save an existing Person entity
	 * 
	 */
	@Transactional
	public void savePerson(Person person) {
		Person existingPerson = personDAO.findPersonByPrimaryKey(person.getId());

		if (existingPerson != null) {
			if (existingPerson != person) {
				existingPerson.setId(person.getId());
				existingPerson.setFirstName(person.getFirstName());
				existingPerson.setLastName(person.getLastName());
				existingPerson.setBirthDate(person.getBirthDate());
				existingPerson.setFunction(person.getFunction());
			}
			person = personDAO.store(existingPerson);
		} else {
			person = personDAO.store(person);
		}
		personDAO.flush();
	}

	/**
	 * Load an existing Person entity
	 * 
	 */
	@Transactional
	public Set<Person> loadPersons() {
		return personDAO.findAllPersons();
	}

	/**
	 * Return all Person entity
	 * 
	 */
	@Transactional
	public List<Person> findAllPersons(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Person>(personDAO.findAllPersons(startResult, maxRows));
	}

	/**
	 * Delete an existing Person entity
	 * 
	 */
	@Transactional
	public void deletePerson(Person person) {
		personDAO.remove(person);
		personDAO.flush();
	}
}
