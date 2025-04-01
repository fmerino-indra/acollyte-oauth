package org.fmm.common.oauth2;

import org.fmm.common.oauth2.adhoc.model.repository.SocialUserRepository;
import org.fmm.common.oauth2.model.config.OAuth2CommonModelConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = {OAuth2CommonModelTestApplication.class})
//@PropertySource(value="classpath:lib-test-application.yaml, factory = YamlPropertySourceFactory.class")
class YamlPropertySourceFactoryTests {
    @Autowired
    SocialUserRepository socialUserRepository;
    
    @Autowired
    OAuth2CommonModelConfig config;

    @Test
	void testProperties() {
    	Assert.isTrue(true, "No se ha conseguido cargar el contexto: O yaml o ...");
	}
    
    @Test
	void testRepository() {
    	Assert.isTrue(true, "No se ha conseguido cargar el contexto: O yaml o ...");
	}
}
