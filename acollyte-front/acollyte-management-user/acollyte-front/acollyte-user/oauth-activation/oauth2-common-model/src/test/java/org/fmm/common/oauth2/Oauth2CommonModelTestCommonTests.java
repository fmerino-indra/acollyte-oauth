package org.fmm.common.oauth2;

import java.util.List;

import org.fmm.common.oauth2.adhoc.model.entity.SocialUser;
import org.fmm.common.oauth2.adhoc.model.repository.SocialUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.util.Assert;


//Aunque está puesto el filtro, no funciona, siempre carga la clase. 
//Parece que el filtro excluye para las entities y los repositorios, que por otro lado,
//Deberían estar aquí, pero como carga por defecto la app, no pueden estar repetidos
@DataJpaTest(showSql = true,  excludeFilters = @Filter(type=FilterType.ASSIGNABLE_TYPE, classes={OAuth2CommonModelTestApplication.class}))

//Esto no funciona porque TestPropertySource sólo funciona con .properties
//@TestPropertySource(locations="classpath:application-test.yaml")

//Esto está mejor en el SpringBootApplication de la parte test
//@PropertySource(value="classpath:lib-test-application.yaml, factory = YamlPropertySourceFactory.class")

// Para que no coja la configuración por defecto de H2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class Oauth2CommonModelTestCommonTests {
    @Autowired
    SocialUserRepository socialUserRepository;
	@Test
	void testJPQL() {
		List<SocialUser> socialUsers = null;
		socialUsers = socialUserRepository.findAll();
		Assert.notNull(socialUsers, "La lista de social users es null");
	}

	@Test
	void listPersonsJPQL() {
		List<SocialUser> socialUsers = null;
		socialUsers = socialUserRepository.findAll();
		Assert.notNull(socialUsers, "La lista de social users es null");
		Assert.notEmpty(socialUsers, "La lista de social users está vacía");
		
		//lenient().when(socialUserRepository.findAll()).then
	}
	
}
