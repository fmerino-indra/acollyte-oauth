package org.fmm.acollyte.common.repository;

import org.fmm.acollyte.common.model.EmailAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface EmailAccountRepository extends JpaRepository<EmailAccount, Integer> {
}
