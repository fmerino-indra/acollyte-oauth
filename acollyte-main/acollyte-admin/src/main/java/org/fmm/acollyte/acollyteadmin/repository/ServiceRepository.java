package org.fmm.acollyte.acollyteadmin.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.fmm.acollyte.common.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("SELECT DISTINCT s FROM Service s"
            + " INNER JOIN FETCH s.serviceTypeBean st")
    List<Service> listServices();

    @Query("SELECT DISTINCT s FROM Service s"
            + " LEFT JOIN FETCH s.serviceTypeBean st"
            + " ORDER BY s.serviceDate")
    List<Service> listOrderedServices();

    @Query("SELECT DISTINCT s FROM Service s "
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " WHERE s.serviceDate >= :from AND s.serviceDate <= :to"
            + " ORDER BY s.serviceDate ASC")
    List<Service> listServicesFromTo(@Param("from")ZonedDateTime from, @Param("to")ZonedDateTime to);
//    @Query("SELECT DISTINCT s FROM Service s "
//            + " INNER JOIN FETCH s.serviceTypeBean st"
//            + " WHERE s.serviceDate >= :from AND s.serviceDate <= :to"
//            + " ORDER BY s.serviceDate ASC")
//    List<Service> listServicesFromTo(@Param("from")LocalDate from, @Param("to")LocalDate to);
}
