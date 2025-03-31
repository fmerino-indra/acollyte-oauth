package org.fmm.acollyte.acollyteadmin;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest(classes = {AcollyteCommonTestApplication.class})
@EntityScan(basePackages = {"org.fmm.common.oauth2.model"})
@EnableJpaRepositories(basePackages = {"org.fmm.common.oauth2.model.repository"})
//@TestPropertySource(locations="classpath:application-test.yaml")
class ResourceActivationTests {
//	@Test
	void testJPQL() {
	}

}
