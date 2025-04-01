package org.fmm.acollyte.acollyteadmin.service;

import java.time.LocalDate;
import java.util.List;

import org.fmm.acollyte.common.model.Absence;
import org.fmm.acollyte.common.model.Person;

/**
 * Los métodos que incluyen sólo el identificdor de la persona, 
 * deberían migrarse al servicio Admin, ya que van a ser invocados por un administrador. 
 * Estos servicios van a ser invocados por la App de móvil que utilizarán los usuarios.  
 * @author x284073
 *
 */
public interface AbsenceMgmtService {

    Absence createAbsence(LocalDate date, Person person);

    /**
     * Save a list of holidays. It will be common call this API with a list of holiday Sundays.
     * @param person
     * @param absenceDates
     * @return
     */
    List<Absence> createAbsencesByPerson(Person person, List<LocalDate> absenceDates);

    /**
     * Save a list of holidays. It will be common call this API with a list of holiday Sundays. 
     * @param idPerson
     * @param absenceDates
     * @return
     */
    List<Absence> createAbsencesByPerson(Integer idPerson, List<LocalDate> absenceDates);

    /**
     * 
     * @param idPerson
     * @param from
     * @param to
     */
    List<Absence> processHolidays(Integer idPerson, LocalDate from, LocalDate to);
}