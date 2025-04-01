package org.fmm.acollyte.acollyteadmin.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import org.fmm.acollyte.acollyteadmin.repository.RaffleRepository;
import org.fmm.acollyte.common.model.Raffle;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationMgmtSericeImpl implements NotificationMgmtService {

    @Autowired
    private RaffleRepository raffleRepository;
    
    @Override
    public void notificate(LocalDateTime from, LocalDateTime to) {
        List<Raffle> raffleList = null;
        raffleList = raffleRepository.selectByDates(
                from.atZone(ZoneId.systemDefault()).toOffsetDateTime(), 
                to.atZone(ZoneId.systemDefault()).toOffsetDateTime());
    }

}
