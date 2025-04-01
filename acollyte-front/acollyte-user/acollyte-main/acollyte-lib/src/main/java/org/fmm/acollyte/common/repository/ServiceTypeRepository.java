package org.fmm.acollyte.common.repository;

import java.util.List;

import org.fmm.acollyte.common.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
    @Query("SELECT DISTINCT st FROM ServiceType st")
    List<ServiceType> listServicesType();
}
