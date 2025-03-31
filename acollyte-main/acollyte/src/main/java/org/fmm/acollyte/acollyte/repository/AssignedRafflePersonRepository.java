package org.fmm.acollyte.acollyte.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.fmm.acollyte.common.model.AssignedRafflePerson;
import org.fmm.acollyte.common.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface AssignedRafflePersonRepository extends JpaRepository<AssignedRafflePerson, Integer> {
    @Query("SELECT DISTINCT arp FROM AssignedRafflePerson arp"
            + " INNER JOIN FETCH arp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH arp.person")
    List<AssignedRafflePerson> listServicePersons();

    @Query("SELECT DISTINCT arp FROM AssignedRafflePerson arp "
            + " INNER JOIN FETCH arp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH arp.person"
            + " WHERE s.serviceDate >= :from"
            + " ORDER BY s.serviceDate")
    List<AssignedRafflePerson> findAssignedServicesFrom(@Param("from") OffsetDateTime from);

    
    @Query("SELECT DISTINCT arp FROM AssignedRafflePerson arp "
            + " INNER JOIN FETCH arp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH arp.person"
            + " WHERE arp.person = :person"
            + " AND s.serviceDate >= :from"
            + " ORDER BY s.serviceDate")
    List<AssignedRafflePerson> findNextAssignedServices(@Param("person") Person person, @Param("from") OffsetDateTime from);

    @Query(value = "SELECT DISTINCT arp FROM AssignedRafflePerson arp "
            + " INNER JOIN FETCH arp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH arp.person"
            + " WHERE arp.person = :person"
            + " AND s.serviceDate >= :from"
            + " AND s.serviceDate <= :to",
//            + " ORDER BY s.serviceDate",
            countQuery = "SELECT count(arp) FROM AssignedRafflePerson arp "
                    + " WHERE arp.person = :person"
                    + " AND arp.raffle.service.serviceDate >= :from"
                    + " AND arp.raffle.service.serviceDate <= :to")

    Page<AssignedRafflePerson> findBeetweenDates(@Param("person") Person person, @Param("from") OffsetDateTime from, @Param("to") OffsetDateTime to, Pageable pageRequest);

    @Query(value = "SELECT DISTINCT arp FROM AssignedRafflePerson arp "
            + " INNER JOIN FETCH arp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH arp.person"
            + " WHERE arp.person = :person"
            + " AND s.serviceDate >= :from"
            + " ORDER BY s.serviceDate",
            countQuery = "SELECT count(arp) FROM AssignedRafflePerson arp "
                    + " WHERE arp.person = :person"
                    + " AND arp.raffle.service.serviceDate >= :from")
    Page<AssignedRafflePerson> findBeetweenDates(@Param("person") Person person, @Param("from") OffsetDateTime from, Pageable pageRequest);
    
    @Query(value = "SELECT DISTINCT arp FROM AssignedRafflePerson arp "
            + " INNER JOIN FETCH arp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH arp.person"
            + " WHERE arp.id = :rafflePersonId")
    Optional<AssignedRafflePerson> findFullById(@Param("rafflePersonId") Integer rafflePersonId);

    @Query("SELECT DISTINCT arp FROM AssignedRafflePerson arp "
            + " INNER JOIN FETCH arp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH arp.person"
            + " WHERE s.serviceDate = :dia"
            + " ORDER BY s.serviceDate")
    List<AssignedRafflePerson> findAssignedServicesByDate(@Param("dia") OffsetDateTime dia);

}
