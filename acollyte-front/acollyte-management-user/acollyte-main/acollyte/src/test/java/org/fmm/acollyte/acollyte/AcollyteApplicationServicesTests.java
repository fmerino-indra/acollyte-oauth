package org.fmm.acollyte.acollyte;

import org.fmm.acollyte.acollyte.service.AcollyteMgmtService;
import org.fmm.acollyte.common.model.AssignedRafflePerson;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes = {AcollyteApplication.class})
@AutoConfigureMockMvc
class AcollyteApplicationServicesTests {
	@Autowired
	PersonRepository personRepository;
	
    @Autowired
    AcollyteMgmtService acollyteMgmtService;
    
    @Test
    void testListRaffle() throws JsonProcessingException, Exception {
    	Person persona = personRepository.findFullPerson(10);
    	Page<AssignedRafflePerson> lista = acollyteMgmtService.nextServices(persona, 0, 1000);
    	Assert.notEmpty(lista.getContent(), "No hay servicios");
    }
    
}
