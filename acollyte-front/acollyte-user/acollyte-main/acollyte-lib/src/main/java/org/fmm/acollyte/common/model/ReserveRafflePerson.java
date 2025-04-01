package org.fmm.acollyte.common.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
public class ReserveRafflePerson extends RafflePerson {

    /**
     * 
     */
    private static final long serialVersionUID = -282145399565715711L;

}
