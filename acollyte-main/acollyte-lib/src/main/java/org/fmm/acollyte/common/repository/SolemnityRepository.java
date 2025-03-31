package org.fmm.acollyte.common.repository;

import java.util.List;
import java.util.Optional;

import org.fmm.acollyte.common.model.Solemnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface SolemnityRepository extends JpaRepository<Solemnity, Integer> {
    @Query("SELECT DISTINCT s FROM Solemnity s "
    		+ "ORDER BY s.date")
    List<Solemnity> listSolemnities();
    
    List<Solemnity> findByOrderByDateAsc();
    
    Optional<Solemnity> findById(Integer id);
}
