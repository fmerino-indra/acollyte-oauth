package org.fmm.acollyte.acollyte.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.fmm.acollyte.acollyte.dto.ConfirmDTO;
import org.fmm.acollyte.acollyte.repository.AssignedRafflePersonRepository;
import org.fmm.acollyte.acollyte.repository.RafflePersonRepository;
import org.fmm.acollyte.common.model.AssignedRafflePerson;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.RafflePerson;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("AcollyteMgmtService")
public class AcollyteMgmtServiceImpl implements AcollyteMgmtService {

    @Autowired
    AssignedRafflePersonRepository assignedRafflePersonRepository;

    @Autowired
    RafflePersonRepository rafflePersonRepository;

    @Autowired
    PersonRepository personRepository;
    
    public void nextServices2(Person person) {
        LocalDate fromLD = null;
        LocalDate toLD = null;
        
        OffsetDateTime from = null;
        OffsetDateTime to = null;
        
        LocalTime time00 = null;
        time00 = LocalTime.of(0, 0);
        
        LocalTime time23 = null;
        time23 = LocalTime.of(23, 59, 59);
       
        fromLD = LocalDate.now();
        toLD = LocalDate.of(2020, 9, 30);

        from = OffsetDateTime.of(fromLD, time00, OffsetDateTime.now().getOffset());
        
        from = prepareFromNow();
        
        to = OffsetDateTime.of(toLD, time23, OffsetDateTime.now().getOffset());
        

        assignedRafflePersonRepository.findBeetweenDates(person, from, to, null);
    }

    @Override
    public Page<AssignedRafflePerson> nextServices(Person person, int page, int pageSize) {
        OffsetDateTime from = null;
//        OffsetDateTime to = null;
        
        PageRequest pr = PageRequest.of(page, pageSize, Sort.by("s.serviceDate").ascending());
        from = prepareFromNow();
//        to = prepareTo();
        return assignedRafflePersonRepository.findBeetweenDates(person, from, pr);
    }
    
    @Override
    public void confirmAttendance(ConfirmDTO confirmDTO) {
        AssignedRafflePerson arp = null;
        Optional<AssignedRafflePerson> opt = null;
        opt = assignedRafflePersonRepository.findFullById(confirmDTO.getAssignedRaffleId());
        if (opt.isPresent()) {
            arp = opt.get();
            if (confirmDTO.getCanGo() != null) {
                arp.setCanGo(confirmDTO.getCanGo());
                if (confirmDTO.getCanGo()) {
                    // Hay que proponer un cambio
                }
            } else if (confirmDTO.getHaveGone() != null) {
                arp.setHaveGone(confirmDTO.getHaveGone());
            } else if (confirmDTO.getHasGone() != null) {
                arp.setHasGone(confirmDTO.getHasGone());
            } 
            assignedRafflePersonRepository.save(arp);
        }
    }
//    private OffsetDateTime prepareTo() {
//        LocalDate toLD = null;
//        
//        OffsetDateTime to = null;
//        
//        LocalTime time23 = null;
//        time23 = LocalTime.of(23, 59, 59);
//       
//        toLD = LocalDate.of(2020, 9, 30);
//        
//        to = OffsetDateTime.of(toLD, time23, OffsetDateTime.now().getOffset());
//        
//        return to;
//    }

    @Override
    public List<? extends RafflePerson> nextServices(Integer personId) {
        Person person = null;
        person = personRepository.findFullPerson(personId);
        return nextServices(person);
    }

    @Override
    public List<? extends RafflePerson> nextServices(String userId) {
        Person person = null;
        person = personRepository.findFullPersonByUserId(userId);
        
        return nextServices(person);
    }
    
    private List<? extends RafflePerson> nextServices(Person person) {
        return rafflePersonRepository.findNextServices(person, prepareFromZonedNow());
    }

    @Deprecated
    private OffsetDateTime prepareFromNow() {
        OffsetDateTime from = null;
        LocalDate fromLD = null;
        LocalTime time00 = null;
        
        
        fromLD = LocalDate.now();
        time00 = LocalTime.of(0, 0);

        from = OffsetDateTime.of(fromLD, time00, OffsetDateTime.now().getOffset());
        return from;
    }

    private ZonedDateTime prepareFromZonedNow() {
        ZonedDateTime from = null;

        LocalDate fromLD = null;
        LocalTime time00 = null;
        
        
        fromLD = LocalDate.now();
        time00 = LocalTime.of(0, 0);

        from = ZonedDateTime.of(fromLD, time00, ZonedDateTime.now().getZone());
        
        return from;
        
        
    }


    @Override
    public Person myInfo(String userId) {
        return personRepository.findFullPersonByUserId(userId);
    }

    @Override
    @Deprecated
    public RafflePerson getRafflePerson(String userId, Integer rafflePersonId) {
        Optional<RafflePerson> rp = rafflePersonRepository.findFullById(rafflePersonId);
        if (rp.isPresent())
            return rp.get();
        else
            return null;
    }

}
