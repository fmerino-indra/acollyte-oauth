package org.fmm.acollyte.acollyte.dto;

import java.io.Serializable;

public class ConfirmDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer assignedRaffleId;

	private Boolean canGo;

	private Boolean hasGone;

    private Boolean haveGone;

	private Integer personId;

    public Boolean getCanGo() {
        return canGo;
    }

    public void setCanGo(Boolean canGo) {
        this.canGo = canGo;
    }

    public Boolean getHasGone() {
        return hasGone;
    }

    public void setHasGone(Boolean hasGone) {
        this.hasGone = hasGone;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getAssignedRaffleId() {
        return assignedRaffleId;
    }

    public void setAssignedRaffleId(Integer assignedRaffleId) {
        this.assignedRaffleId = assignedRaffleId;
    }

    public Boolean getHaveGone() {
        return haveGone;
    }

    public void setHaveGone(Boolean haveGone) {
        this.haveGone = haveGone;
    }

}