package com.data.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllPersons", query = "select myPerson from Person myPerson"),
		@NamedQuery(name = "findPersonByBirthDate", query = "select myPerson from Person myPerson where myPerson.birthDate = ?1"),
		@NamedQuery(name = "findPersonByBirthDateAfter", query = "select myPerson from Person myPerson where myPerson.birthDate > ?1"),
		@NamedQuery(name = "findPersonByBirthDateBefore", query = "select myPerson from Person myPerson where myPerson.birthDate < ?1"),
		@NamedQuery(name = "findPersonByFirstName", query = "select myPerson from Person myPerson where myPerson.firstName = ?1"),
		@NamedQuery(name = "findPersonByFirstNameContaining", query = "select myPerson from Person myPerson where myPerson.firstName like ?1"),
		@NamedQuery(name = "findPersonByFunction", query = "select myPerson from Person myPerson where myPerson.function = ?1"),
		@NamedQuery(name = "findPersonByFunctionContaining", query = "select myPerson from Person myPerson where myPerson.function like ?1"),
		@NamedQuery(name = "findPersonById", query = "select myPerson from Person myPerson where myPerson.id = ?1"),
		@NamedQuery(name = "findPersonByLastName", query = "select myPerson from Person myPerson where myPerson.lastName = ?1"),
		@NamedQuery(name = "findPersonByLastNameContaining", query = "select myPerson from Person myPerson where myPerson.lastName like ?1"),
		@NamedQuery(name = "findPersonByPrimaryKey", query = "select myPerson from Person myPerson where myPerson.id = ?1") })
@Table(catalog = "webdata", name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "WebEnd/com/data/domain", name = "Person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 */

	@Column(name = "first_name", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String firstName;
	/**
	 */

	@Column(name = "last_name", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lastName;
	/**
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar birthDate;
	/**
	 */

	@Column(name = "function", length = 45)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String function;

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 */
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 */
	public Calendar getBirthDate() {
		return this.birthDate;
	}

	/**
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 */
	public String getFunction() {
		return this.function;
	}

	/**
	 */
	public Person() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Person that) {
		setId(that.getId());
		setFirstName(that.getFirstName());
		setLastName(that.getLastName());
		setBirthDate(that.getBirthDate());
		setFunction(that.getFunction());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("firstName=[").append(firstName).append("] ");
		buffer.append("lastName=[").append(lastName).append("] ");
		buffer.append("birthDate=[").append(birthDate).append("] ");
		buffer.append("function=[").append(function).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((id == null) ? 0 : id.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person equalCheck = (Person) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
