package org.fmm.common.oauth2.crypto;

import org.fmm.common.oauth2.crypto.config.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="classpath:lib-test-application.yaml", factory = YamlPropertySourceFactory.class)
public class OAuth2CommonActivationTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2CommonActivationTestApplication.class, args);
	}

}
