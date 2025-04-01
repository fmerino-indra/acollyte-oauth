package org.fmm.common.oauth2.adhoc.model.repository;

import java.util.Optional;

import org.fmm.common.oauth2.adhoc.model.entity.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SocialUserRepository extends JpaRepository<SocialUser, Integer> {

	Optional<SocialUser> findByEmail(String email);
	Boolean existsByEmail(String email);

    @Query("SELECT ss FROM SocialUser ss"
            + " WHERE ss.providerId = :subject")
    Optional<SocialUser> findUserByProviderId(@Param("subject") String subject);

}
