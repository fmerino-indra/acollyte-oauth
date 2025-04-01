package org.fmm.acollyte.common.model;

import java.io.Serializable;
import java.util.List;

import org.fmm.common.oauth2.adhoc.model.entity.SocialUser;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(columnDefinition = "serial")
	private Integer id;

	private Integer comunidad;

	private String name;

	@Column(name="user_id")
	private String userId;
	
	//bi-directional many-to-one association to SocialUser
	@ManyToOne
	@JoinColumn(name="social_user_id")
	private SocialUser socialUserId;

	//bi-directional many-to-one association to EmailAccount
	@OneToOne(mappedBy="person")
	private EmailAccount emailAccount;

	//bi-directional many-to-one association to MobileNumber
	@OneToOne(mappedBy="person")
	private MobileNumber mobileNumber;

	//bi-directional many-to-many association to AppRole
	@ManyToMany
	@JoinTable(
		name="person_app_role"
		, joinColumns={
			@JoinColumn(name="person_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="app_role_id")
			}
		)
	private List<AppRole> appRoles;

	public Person() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComunidad() {
		return this.comunidad;
	}

	public void setComunidad(Integer comunidad) {
		this.comunidad = comunidad;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	public SocialUser getSocialUserId() {
		return socialUserId;
	}

	public void setSocialUserId(SocialUser socialUserId) {
		this.socialUserId = socialUserId;
	}
	
	public List<AppRole> getAppRoles() {
		return appRoles;
	}
	
	public void setAppRoles(List<AppRole> appRoles) {
		this.appRoles = appRoles;
	}

	@Override
	public String toString() {
	    return this.name;
	}
	
	@Override
    public boolean equals(Object obj) {
	    if (obj instanceof Person)
	        return this.equals((Person)obj);
	    else
	        return super.equals(obj);
    }
    public boolean equals(Person otherPerson) {
        return this.getId().equals(otherPerson.getId());
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

	public EmailAccount getEmailAccount() {
		return emailAccount;
	}

	public void setEmailAccount(EmailAccount emailAccount) {
		this.emailAccount = emailAccount;
	}

	public MobileNumber getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(MobileNumber mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}