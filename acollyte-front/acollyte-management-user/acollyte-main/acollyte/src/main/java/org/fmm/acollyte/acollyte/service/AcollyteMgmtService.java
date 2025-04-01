package org.fmm.acollyte.acollyte.service;

import java.util.List;

import org.fmm.acollyte.acollyte.dto.ConfirmDTO;
import org.fmm.acollyte.common.model.AssignedRafflePerson;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.RafflePerson;
import org.springframework.data.domain.Page;

public interface AcollyteMgmtService {

    Person myInfo(String userId);
    List<? extends RafflePerson> nextServices(Integer id);
    List<? extends RafflePerson> nextServices(String userId);
    @Deprecated
    RafflePerson getRafflePerson(String userId, Integer rafflePersonId);
    
//    void nextServices(Person person);
    Page<AssignedRafflePerson> nextServices(Person person, int page, int pageSize);
    
    void confirmAttendance(ConfirmDTO confirmDTO);
}