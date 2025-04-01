package org.fmm.acollyte.acollyteadmin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.fmm.acollyte.acollyteadmin.repository.AbsenceRepository;
import org.fmm.acollyte.common.model.Absence;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AbsenceMgmtService")
public class AbsenceMgmtServiceImpl implements AbsenceMgmtService {

    @Autowired
    private AbsenceRepository absenceRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Override
    public Absence createAbsence(LocalDate date, Person person) {
        Absence absence = new Absence();
        absence.setDay(date);
        absence.setPerson(person);
        return absenceRepository.save(absence);
    }

    @Override
    public List<Absence> createAbsencesByPerson(Person person, List<LocalDate> absenceDates) {
        List<Absence> response = null;
        response = new ArrayList<>(absenceDates.size());
        
        for (LocalDate date: absenceDates) {
            response.add(createAbsence(date, person));
        }
        return response;
    }
    
    @Override
    public List<Absence> createAbsencesByPerson(Integer idPerson, List<LocalDate> absenceDates) {
        Person person = null;

        person = personRepository.findFullPerson(idPerson);

        return createAbsencesByPerson(person, absenceDates);
    }
    
    @Override
    public List<Absence> processHolidays(Integer idPerson, LocalDate from, LocalDate to) {
        Person p = null;

        List<LocalDate> sundaysHolydayList = DateUtil.sundaysBetween(from, to);

        p = personRepository.findFullPerson(idPerson);
        return createAbsencesByPerson(p, sundaysHolydayList);
    }
    
    
}
