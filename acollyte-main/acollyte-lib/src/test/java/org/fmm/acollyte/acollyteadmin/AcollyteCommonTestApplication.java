package org.fmm.acollyte.acollyteadmin;

import org.fmm.common.oauth2.model.config.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"org.fmm.common.oauth2.model"})
@EnableJpaRepositories(basePackages = {"org.fmm.common.oauth2.model.repository"})
@PropertySource(value="classpath:lib-test-application.yaml", factory = YamlPropertySourceFactory.class)
public class AcollyteCommonTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcollyteCommonTestApplication.class, args);
	}

}
