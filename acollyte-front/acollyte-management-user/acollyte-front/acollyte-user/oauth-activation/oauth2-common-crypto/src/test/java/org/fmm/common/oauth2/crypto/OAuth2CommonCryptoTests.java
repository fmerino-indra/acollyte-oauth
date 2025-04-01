package org.fmm.common.oauth2.crypto;

import org.fmm.common.oauth2.crypto.config.OAuth2CommonCryptoProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest(classes = {OAuth2CommonActivationTestApplication.class})

@PropertySource(value="classpath:lib-test-application.yaml, factory = YamlPropertySourceFactory.class")
class OAuth2CommonCryptoTests {
    @Autowired
    OAuth2CommonCryptoProperties properties;
	@Test
	void testProperties() {
		properties.getProviderMapProperties();
	}

}
