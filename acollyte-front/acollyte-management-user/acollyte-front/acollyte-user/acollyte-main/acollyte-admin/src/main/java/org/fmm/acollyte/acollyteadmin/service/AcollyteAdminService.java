package org.fmm.acollyte.acollyteadmin.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.fmm.acollyte.common.model.Service;

public interface AcollyteAdminService {

    Service createService(Service service);

    Service updateService(Service service);

    List<Service> createSundays(LocalDate from, LocalDate to, LocalTime time);

    /** 
     * Basado en el algoritmo de Fisher-Yates
     * @param persons
     * @return reordered persons
     */
//    RaffleDTO raffle(List<Person> persons);

//    Map<LocalDate, List<Person>> prepareRaffle(List<Service> services);

    RaffleDTO prepareRaffle(LocalDate fromLDT, LocalDate toLDT);

    RaffleDTO raffle(RaffleDTO raffle);

}