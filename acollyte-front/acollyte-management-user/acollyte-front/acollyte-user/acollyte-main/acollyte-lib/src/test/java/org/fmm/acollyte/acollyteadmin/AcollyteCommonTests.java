package org.fmm.acollyte.acollyteadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.fmm.acollyte.common.model.AppPermission;
import org.fmm.acollyte.common.model.AppRole;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.repository.AppPermissionRepository;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.fmm.common.oauth2.adhoc.model.entity.SocialUser;
import org.fmm.common.oauth2.adhoc.model.repository.SocialUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.Assert;

@SpringBootTest(classes = {AcollyteCommonTestApplication.class})
@EntityScan(basePackages = {"org.fmm.acollyte.common","org.fmm.common.oauth2.model"})
@EnableJpaRepositories(basePackages = {"org.fmm.acollyte.common","org.fmm.acollyte.acollyteadmin", "org.fmm.common.oauth2.model.repository"})
//@TestPropertySource(locations="classpath:application-test.yaml")
class AcollyteCommonTests {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    AppPermissionRepository permissionRepository;

    @Autowired
    SocialUserRepository socialUserRepository;
//	@Test
	void testJPQL() {
	}

//	@Test
	void listPersonsJPQL() {
		List<Person> personas = null;
		personas = personRepository.listAllPerson();
		Assert.notNull(personas, "La lista de personas es vacía");
	}
	
//	@Test
	void findPersonJPQL() {
		Optional<Person> persona = null;
		persona = personRepository.findById(10);
		Assert.notNull(persona, "El objeto Optional<Person> es null");
		Assert.isTrue(persona.isPresent(), "No hay objeto Person dentro del opcional");
	}
	
	@Test
	void findPersonPermissionJPQL() {
		Integer suId = 3;
		Optional<SocialUser> suOp = socialUserRepository.findById(suId);
		Assert.isTrue(suOp.isPresent(), "Debe existir el social user");
		Person persona = null;
		persona = personRepository.findPersonPermissionsBySocialUser(suOp.get());
		Assert.notNull(persona, "La persona debe encontrarse");
		List<AppPermission> permisos = new ArrayList<>();
		for (AppRole appRole: persona.getAppRoles()) {
			permisos.addAll(permissionRepository.listPermissionsByRole(appRole.getId()));
		}
		Assert.notEmpty(permisos, "Permisos debe contener algún dato");
	}
}
