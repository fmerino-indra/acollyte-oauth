package org.fmm.acollyte.common.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Solemnity.findAll", query="SELECT s FROM Solemnity s")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")
public class Solemnity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	private boolean definite;
	
	private LocalDate date;
	
	private short day;
	
	private short month;


	public Solemnity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isDefinite() {
		return definite;
	}

	public void setDefinite(boolean definite) {
		this.definite = definite;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public short getDay() {
		return day;
	}

	public void setDay(short day) {
		this.day = day;
	}

	public short getMonth() {
		return month;
	}

	public void setMonth(short month) {
		this.month = month;
	}

	@Override
    public boolean equals(Object obj) {
	    if (obj instanceof Solemnity)
	        return this.equals((Solemnity)obj);
	    else
	        return super.equals(obj);
    }
    public boolean equals(Solemnity otherPerson) {
        return this.getId().equals(otherPerson.getId());
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}