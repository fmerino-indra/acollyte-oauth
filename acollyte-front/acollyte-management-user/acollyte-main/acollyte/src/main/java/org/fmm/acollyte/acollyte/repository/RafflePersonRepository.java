package org.fmm.acollyte.acollyte.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.RafflePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface RafflePersonRepository extends JpaRepository<RafflePerson, Integer> {
    @Query("SELECT DISTINCT rp FROM RafflePerson rp "
            + " INNER JOIN FETCH rp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH rp.person"
            + " WHERE rp.person = :person"
            + " AND s.serviceDate >= :from"
            + " ORDER BY s.serviceDate")
    List<RafflePerson> findNextServices(@Param("person") Person person, @Param("from") ZonedDateTime from);

    @Query(value = "SELECT DISTINCT rp FROM RafflePerson rp "
            + " INNER JOIN FETCH rp.raffle r"
            + " INNER JOIN FETCH r.service s"
            + " INNER JOIN FETCH s.serviceTypeBean st"
            + " INNER JOIN FETCH rp.person"
            + " WHERE rp.id = :rafflePersonId")
    Optional<RafflePerson> findFullById(@Param("rafflePersonId") Integer rafflePersonId);
}
