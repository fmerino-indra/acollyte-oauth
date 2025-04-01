package org.fmm.acollyte.acollyte.dto;

import java.time.ZonedDateTime;

public class RafflePersonDTO {
    private Integer personId;
    private Integer rafflePersonId;
    private Integer raffleId;
    
    private String name;
    private String email;
    private String phoneNumber;
    private Integer comunidad;
    
    private ZonedDateTime serviceDate;
    private String serviceName;
    private Boolean canGo;
    private Boolean haveGone;
    
    public Integer getPersonId() {
        return personId;
    }
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public ZonedDateTime getServiceDate() {
        return serviceDate;
    }
    public void setServiceDate(ZonedDateTime serviceDate) {
        this.serviceDate = serviceDate;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public Boolean getCanGo() {
        return canGo;
    }
    public void setCanGo(Boolean canGo) {
        this.canGo = canGo;
    }
    public Boolean getHaveGone() {
        return haveGone;
    }
    public void setHaveGone(Boolean haveGone) {
        this.haveGone = haveGone;
    }
    public Integer getRafflePersonId() {
        return rafflePersonId;
    }
    public void setRafflePersonId(Integer rafflePersonId) {
        this.rafflePersonId = rafflePersonId;
    }
    public Integer getRaffleId() {
        return raffleId;
    }
    public void setRaffleId(Integer raffleId) {
        this.raffleId = raffleId;
    }
    public Integer getComunidad() {
        return comunidad;
    }
    public void setComunidad(Integer comunidad) {
        this.comunidad = comunidad;
    }
}
