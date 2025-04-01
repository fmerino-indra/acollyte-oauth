package org.fmm.acollyte.acollyteadmin.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.fmm.acollyte.common.model.AssignedRafflePerson;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.RafflePerson;
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
public interface AssignedRafflePersonRepository extends JpaRepository<AssignedRafflePerson, Integer> {
    @Query("SELECT DISTINCT rp FROM RafflePerson rp"
            + " INNER JOIN FETCH rp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH rp.person")
    List<RafflePerson> listServicePersons();
    
    @Query("SELECT DISTINCT rp FROM RafflePerson rp"
            + " INNER JOIN FETCH rp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH rp.person"
            + " WHERE rp.person = :person"
            + " AND (r.service = :service)"
            + " ORDER BY s.serviceDate")
    RafflePerson findServicesByPersonAndService(@Param("person") Person person, @Param("service") Service service);
    
    @Query("SELECT DISTINCT rp FROM RafflePerson rp"
            + " INNER JOIN FETCH rp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH rp.person"
            + " WHERE s.serviceDate >= :from"
            + " AND s.serviceDate <= :to")
    List<RafflePerson> listRafflePersonsBeetweenDates(@Param("from")LocalDateTime from, @Param("to")LocalDateTime to);
    
}
