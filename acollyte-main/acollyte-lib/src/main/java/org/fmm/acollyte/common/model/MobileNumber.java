package org.fmm.acollyte.common.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the mobile_number database table.
 * 
 */
@Entity
@Table(name="mobile_number")
@NamedQuery(name="MobileNumber.findAll", query="SELECT m FROM MobileNumber m")
public class MobileNumber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="mobile_number")
	private String mobileNumber;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	public MobileNumber() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
    public String toString() {
//        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);

        return ReflectionToStringBuilder.toStringExclude(this, "id", "person");

//        return new ReflectionToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE, null, null, false, false)
//                .append("mobileNumber", mobileNumber)
//                .toString();
    }

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}