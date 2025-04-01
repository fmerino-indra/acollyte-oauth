package org.fmm.acollyte.common.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.collection.spi.PersistentBag;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Boolean eve;

	private Boolean fixed;

//	@Temporal(TemporalType.DATE)
	@Column(name="service_date", columnDefinition = "DATE")
//    @Column(name="service_date")
	private ZonedDateTime serviceDate;

	@Column(name="service_name")
	private String serviceName;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="service_type")
	private ServiceType serviceTypeBean;

	//bi-directional many-to-one association to ServicePerson
//	@OneToMany(mappedBy="service")
//	private List<RafflePerson> rafflePersons;

    @OneToMany(mappedBy="service")
    @JsonIgnore
    private List<Raffle> raffles;

    private Integer needed;
    private Integer nReserves;
    
	public Service() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEve() {
		return this.eve;
	}

	public void setEve(Boolean eve) {
		this.eve = eve;
	}

	public Boolean getFixed() {
		return this.fixed;
	}

	public void setFixed(Boolean fixed) {
		this.fixed = fixed;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public ServiceType getServiceTypeBean() {
		return this.serviceTypeBean;
	}

	public void setServiceTypeBean(ServiceType serviceTypeBean) {
		this.serviceTypeBean = serviceTypeBean;
	}

    public List<Raffle> getRaffles() {
        try {
            if (this.raffles instanceof PersistentBag)
                this.raffles = new ArrayList<Raffle>();
        } catch (Exception e) {
            this.raffles = new ArrayList<Raffle>();
        }
        return this.raffles;
    }

    public void setRaffles(List<Raffle> raffles) {
        this.raffles = raffles;
    }

//	public List<RafflePerson> getServicePersons() {
//	    try {
//	        if (this.rafflePersons instanceof PersistentBag)
//	            this.rafflePersons = new ArrayList<RafflePerson>();
//	    } catch (Exception e) {
//            this.rafflePersons = new ArrayList<RafflePerson>();
//	    }
//		return this.rafflePersons;
//	}
//
//	public void setServicePersons(List<RafflePerson> servicePersons) {
//		this.rafflePersons = servicePersons;
//	}

//	public ServicePerson addServicePerson(ServicePerson servicePerson) {
//		getServicePersons().add(servicePerson);
//		servicePerson.setService(this);
//
//		return servicePerson;
//	}

//	public ServicePerson removeServicePerson(ServicePerson servicePerson) {
//		getServicePersons().remove(servicePerson);
//		servicePerson.setService(null);
//
//		return servicePerson;
//	}

//	public ServicePerson addPerson(Person person) {
//	    ServicePerson sp = new ServicePerson();
//	    sp.setService(this);
//	    sp.setAssigned(person);
//	    this.getServicePersons().add(sp);
//	    
//	    return sp;
//	}
	
    public ZonedDateTime getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(ZonedDateTime serviceDate) {
        this.serviceDate = serviceDate;
    }
    
    @Override
    public String toString() {
        return this.serviceDate + "|" + this.serviceName;
    }

    public Integer getNeeded() {
        return needed;
    }

    public void setNeeded(Integer needed) {
        this.needed = needed;
    }

    public Integer getnReserves() {
        return nReserves;
    }

    public void setnReserves(Integer nReserves) {
        this.nReserves = nReserves;
    }
}