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
		@NamedQuery(name = "findAllCompanys", query = "select myCompany from Company myCompany"),
		@NamedQuery(name = "findCompanyByAddress", query = "select myCompany from Company myCompany where myCompany.address = ?1"),
		@NamedQuery(name = "findCompanyByAddressContaining", query = "select myCompany from Company myCompany where myCompany.address like ?1"),
		@NamedQuery(name = "findCompanyByCreationDate", query = "select myCompany from Company myCompany where myCompany.creationDate = ?1"),
		@NamedQuery(name = "findCompanyById", query = "select myCompany from Company myCompany where myCompany.id = ?1"),
		@NamedQuery(name = "findCompanyByName", query = "select myCompany from Company myCompany where myCompany.name = ?1"),
		@NamedQuery(name = "findCompanyByNameContaining", query = "select myCompany from Company myCompany where myCompany.name like ?1"),
		@NamedQuery(name = "findCompanyByPrimaryKey", query = "select myCompany from Company myCompany where myCompany.id = ?1") })
@Table(catalog = "webdata", name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "WebEnd/com/data/domain", name = "Company")
public class Company implements Serializable {
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

	@Column(name = "name", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "address", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String address;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar creationDate;

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
	public void setName(String name) {
		this.name = name;
	}

	/**
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 */
	public Calendar getCreationDate() {
		return this.creationDate;
	}

	/**
	 */
	public Company() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Company that) {
		setId(that.getId());
		setName(that.getName());
		setAddress(that.getAddress());
		setCreationDate(that.getCreationDate());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("address=[").append(address).append("] ");
		buffer.append("creationDate=[").append(creationDate).append("] ");

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
		if (!(obj instanceof Company))
			return false;
		Company equalCheck = (Company) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
