package org.fmm.common.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"org.fmm.common.oauth2.model.entity"})
@EnableJpaRepositories(basePackages = {"org.fmm.common.oauth2.model.repository"})
//@PropertySource(value="classpath:lib-test-application.yaml", factory = YamlPropertySourceFactory.class)
public class OAuth2CommonModelTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2CommonModelTestApplication.class, args);
	}

}
