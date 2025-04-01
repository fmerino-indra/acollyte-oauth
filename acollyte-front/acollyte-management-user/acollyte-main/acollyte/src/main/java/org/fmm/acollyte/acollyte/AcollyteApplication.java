package org.fmm.acollyte.acollyte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.fmm.common","org.fmm.acollyte"})
@EntityScan(basePackages = {"org.fmm.acollyte.common", "org.fmm.common.oauth2.model"})
@EnableJpaRepositories(basePackages = {"org.fmm.acollyte.common","org.fmm.acollyte.acollyte", "org.fmm.common.oauth2.model"})

public class AcollyteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcollyteApplication.class, args);
	}

}
