package org.fmm.acollyte.acollyte;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.fmm.acollyte.acollyte.dto.ConfirmDTO;
import org.fmm.acollyte.acollyte.dto.RafflePersonDTO;
import org.fmm.acollyte.acollyte.repository.AssignedRafflePersonRepository;
import org.fmm.acollyte.acollyte.repository.RafflePersonRepository;
import org.fmm.acollyte.acollyte.repository.SubstitutionRepository;
import org.fmm.acollyte.acollyte.service.AcollyteMgmtService;
import org.fmm.acollyte.common.model.AssignedRafflePerson;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.RafflePerson;
import org.fmm.acollyte.common.model.Substitution;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest(classes = {AcollyteApplication.class})
@AutoConfigureMockMvc
class AcollyteApplicationTests {

    @Autowired
    AssignedRafflePersonRepository assignedRafflePersonRepository;
    
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    SubstitutionRepository substitutionRepository;
    
    @Autowired
    RafflePersonRepository rafflePersonRepository;
    
    @Autowired
    AcollyteMgmtService acollyteMgmtService;
    
    @Autowired
    private MockMvc mvc;

    //@Test
    void makeSubstitutions() throws JsonProcessingException, Exception {
        List<Substitution> lista = null;
        List<AssignedRafflePerson> assignedList = null;
        Substitution s = null;
        Person substitute = null;
        
        LocalDate fromLD = null;
        
        OffsetDateTime from = null;
        
        LocalTime time00 = null;
        time00 = LocalTime.of(12, 0);
        
        fromLD = LocalDate.of(2020, 8, 2);
        from = OffsetDateTime.of(fromLD, time00, OffsetDateTime.now().getOffset());
        
        assignedList = assignedRafflePersonRepository.findAssignedServicesFrom(from);
        
        for (AssignedRafflePerson arp : assignedList) {
            arp.getPerson();
        }
        
        AssignedRafflePerson arp = null;
        Optional<AssignedRafflePerson> opt = null;
        
        assignedList = assignedRafflePersonRepository.findAssignedServicesByDate(from);
        for (AssignedRafflePerson arp2 : assignedList) {
            substitute = personRepository.findFullPersonByUserId("pguerrero");
            
            s = new Substitution();
            s.setPerson(substitute);
            s.setRafflePerson(arp2);
            substitutionRepository.save(s);
            
//            arp2.getPerson();
//            arp2.addSubstitution(s);
        }
        
//        if (opt.isPresent()) {
//            
//        }
        
        
        
        lista = substitutionRepository.listSubstitutions();
        System.out.println("Hi");
    }


    @Test
    void testListRaffleMVC() throws JsonProcessingException, Exception {
      ObjectMapper objectMapper = null;
      ResultActions resultActions = null;
      MvcResult mvcResult = null;
      String contentAsString = null;
      List<? extends RafflePersonDTO> resultado = null;
      
      resultActions = mvc.perform(get("/acollyte/"+"felix"+"/raffle"))
              .andDo(print())
              .andExpect(status().isOk());
      mvcResult = resultActions.andReturn();
      contentAsString = mvcResult.getResponse().getContentAsString();
      System.out.println(contentAsString);
      objectMapper = new ObjectMapper();
      
      objectMapper = Jackson2ObjectMapperBuilder.json()
              .modules(new JavaTimeModule())
              .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
              .build();
      
      resultado = objectMapper.readValue(contentAsString, new TypeReference<List<RafflePersonDTO>>() {});
      
    }
    
//  @Test
  void testListSubstitution() throws JsonProcessingException, Exception {
      List<Substitution> lista = null;
      lista = substitutionRepository.listSubstitutions();
      System.out.println("Hi");
  }

//    @Test
//    @Transactional
    void testConfirmMVC() throws JsonProcessingException, Exception {
        ObjectMapper objectMapper = null;
        objectMapper = new ObjectMapper();

        ConfirmDTO confirmDto = null;
        // Jacobo, 19/07/2020
        
        confirmDto = new ConfirmDTO();
        confirmDto.setPersonId(7);
        confirmDto.setAssignedRaffleId(711);
        confirmDto.setHaveGone(true);
//        mvc.perform(patch("/acollyte/raffle/"+confirmDto.getPersonId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(confirmDto)))
//        .andExpect(status().isOk());

        mvc.perform(patch("/acollyte/"+confirmDto.getPersonId()+"/raffle/"+confirmDto.getAssignedRaffleId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(confirmDto)))
        .andExpect(status().isOk());

//        confirmDto = new ConfirmDTO();
//        confirmDto.setPersonId(7);
//        confirmDto.setAssignedRaffleId(711);
//        confirmDto.setCanGo(true);
//        mvc.perform(patch("/acollyte/raffle/"+confirmDto.getPersonId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(confirmDto)))
//        .andExpect(status().isOk());

//        confirmDto = new ConfirmDTO();
//        confirmDto.setPersonId(7);
//        confirmDto.setAssignedRaffleId(711);
//        confirmDto.setHasGone(true);
//        mvc.perform(patch("/acollyte/raffle/"+confirmDto.getPersonId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(confirmDto)))
//        .andExpect(status().isOk());

    }
//	@Test
	void testJPQL() {
	    List<AssignedRafflePerson> servicePersons;
	    servicePersons=assignedRafflePersonRepository.listServicePersons();
	    System.out.println(servicePersons);
	}

//    @Test
    void testJPQLFindeNext() {
        List<RafflePerson> rafflePersons;
        List<AssignedRafflePerson> assignedRafflePersons;
        Page<AssignedRafflePerson> pageOfPersons;
        
        Person person = null;
        
        LocalDate fromLD = null;
        LocalDate toLD = null;
        
        OffsetDateTime from = null;
        OffsetDateTime to = null;
        
        LocalTime time00 = null;
        time00 = LocalTime.of(0, 0);
        
        LocalTime time23 = null;
        time23 = LocalTime.of(23, 59, 59);
        
        person = new Person();
        person.setId(1);
        
        fromLD = LocalDate.of(2020, 7, 1);
        toLD = LocalDate.of(2022, 9, 30);

        from = OffsetDateTime.of(fromLD, time00, OffsetDateTime.now().getOffset());
        to = OffsetDateTime.of(toLD, time23, OffsetDateTime.now().getOffset());
        
//        PageRequest page = PageRequest.of(0,1);
//        rp=servicePersonRepository.findNextService(person, from, page);
//        System.out.println(rp);

        // Mientras offset
//        rafflePersons=rafflePersonRepository.findNextServices(person, from);
//        System.out.println(rafflePersons);

        assignedRafflePersons=assignedRafflePersonRepository.findNextAssignedServices(person, from);
        System.out.println(assignedRafflePersons);

        pageOfPersons=assignedRafflePersonRepository.findBeetweenDates(person, from, to, PageRequest.of(0, 3));
        System.out.println(pageOfPersons);
        
        pageOfPersons=acollyteMgmtService.nextServices(person, 0, 3);
        System.out.println(pageOfPersons);
    }

}
