package org.fmm.acollyte.common.repository;

import java.util.List;

import org.fmm.acollyte.common.model.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface AppPermissionRepository extends JpaRepository<AppPermission, Integer> {
    @Query(value = "SELECT p.* FROM app_permission p "
    		+ " LEFT JOIN app_role_permission rp on rp.permission_id = p.id"
    		+ " WHERE rp.role_id = :appRole",
    		nativeQuery = true)
    List<AppPermission> listPermissionsByRole(@Param("appRole") Integer appRole);
    
}
