package org.fmm.acollyte.acollyteadmin.service;

import org.fmm.acollyte.common.model.Person;

public class RaffledPersonDTO {
    private Person assigned;
    private Person reserve;
    public Person getAssigned() {
        return assigned;
    }
    public void setAssigned(Person assigned) {
        this.assigned = assigned;
    }
    public Person getReserve() {
        return reserve;
    }
    public void setReserve(Person reserve) {
        this.reserve = reserve;
    }
}
