package com.data.Domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Entity;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@Table(catalog = "webdata", name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "WebEnd/com/data/domain", name = "Company")
@Entity
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Company(String name, String address, String creationDate){
		this.name = name;
		this.address = address;
		String cDate = creationDate;
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
		try {
			this.creationDate = Calendar.getInstance();
			this.creationDate.setTime(df.parse(creationDate));
		} catch (ParseException e) {
			try {
				this.creationDate.setTime(df.parse(new Date().toString()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 */

	@Column(name = "id", nullable = false)
	@GeneratedValue
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
