package org.fmm.acollyte.acollyte.repository;

import java.util.List;

import org.fmm.acollyte.common.model.Substitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface SubstitutionRepository extends JpaRepository<Substitution, Integer> {
    @Query("SELECT DISTINCT s FROM Substitution s"
            + " INNER JOIN FETCH s.rafflePerson rp"
            + " INNER JOIN FETCH rp.raffle r"
            + " INNER JOIN FETCH r.service srv"
            + " INNER JOIN FETCH srv.serviceTypeBean st"
            + " INNER JOIN FETCH rp.person")
    List<Substitution> listSubstitutions();
}
