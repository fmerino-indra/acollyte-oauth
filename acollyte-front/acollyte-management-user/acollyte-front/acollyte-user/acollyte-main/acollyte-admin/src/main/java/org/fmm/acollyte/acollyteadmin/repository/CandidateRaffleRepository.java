package org.fmm.acollyte.acollyteadmin.repository;

import java.util.List;

import org.fmm.acollyte.common.model.CandidatesRaffle;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface CandidateRaffleRepository extends JpaRepository<CandidatesRaffle, Integer> {
    @Query("SELECT cp FROM CandidatesRaffle cp"
            + " INNER JOIN FETCH cp.raffle r"
            + " INNER JOIN FETCH cp.person p"
            + " WHERE r = :raffle")
    List<CandidatesRaffle> listByRaffle(@Param("raffle")Raffle raffle);

    @Query("SELECT cp FROM CandidatesRaffle cp"
            + " INNER JOIN FETCH cp.raffle r"
            + " INNER JOIN FETCH cp.person p"
            + " WHERE r = :raffle"
            + " AND p = :person")
    CandidatesRaffle findByRaffleAndPerson(@Param("raffle")Raffle raffle, @Param("person") Person person);
}
