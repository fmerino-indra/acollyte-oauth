package org.fmm.acollyte.acollyteadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"org.fmm.acollyte.common"})
@EnableJpaRepositories(basePackages = {"org.fmm.acollyte.common","org.fmm.acollyte.acollyteadmin"})
public class AcollyteAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcollyteAdminApplication.class, args);
	}

}
