package org.fmm.acollyte.acollyteadmin.service;

import java.time.LocalDateTime;

public interface NotificationMgmtService {

    void notificate(LocalDateTime from, LocalDateTime to);
}