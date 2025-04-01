package org.fmm.acollyte.acollyte.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.fmm.acollyte.acollyte.dto.ConfirmDTO;
import org.fmm.acollyte.acollyte.dto.PersonDTO;
import org.fmm.acollyte.acollyte.dto.RafflePersonDTO;
import org.fmm.acollyte.acollyte.service.AcollyteMgmtService;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.RafflePerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acollyte")
public class RaffleController {

	private static final Logger logger = LoggerFactory.getLogger(RaffleController.class);

    @Autowired
    private AcollyteMgmtService acollyteMgmtService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('me')")
    public ResponseEntity<PersonDTO> myInfo(@PathVariable(name = "userId") String userId, Principal principal, Authentication authentication) {
    	logger.debug(userId);
        PersonDTO dto = null;
        dto = toPersonDTO(acollyteMgmtService.myInfo(userId));
        return ResponseEntity.ok(dto);
    }

    /**
     * Como "BaaS" API debería ser: /acollyte/raffle Y el userId debería ponerlo el API Gateway extraído del JWT
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/raffle")
    public ResponseEntity<List<RafflePersonDTO>> nextServices(@PathVariable(name = "userId") String userId) {
        List<? extends RafflePerson> lista = acollyteMgmtService.nextServices(userId);
        return ResponseEntity.ok(toDTO(lista));
    }
    
    @PatchMapping("/{userId}/raffle/{raffleId}")
    public ResponseEntity<String> confirmAttendance(@PathVariable(name = "userId") String userId, @PathVariable(name = "raffleId") Integer raffleId, @RequestBody ConfirmDTO confirmDTO) {
        acollyteMgmtService.confirmAttendance(confirmDTO);
        return ResponseEntity.ok("Raffle Person updated");
    }
    
    @GetMapping("/{userId}/raffle/{raffleId}")
    @Deprecated
    public ResponseEntity<RafflePerson> getRafflePerson(@PathVariable(name = "userId") String userId, @PathVariable(name = "raffleId") Integer rafflePersonId) {
        return ResponseEntity.ok(acollyteMgmtService.getRafflePerson(userId, rafflePersonId));
    }
    
//    @GetMapping("/hello")
//    public ResponseEntity<String> hello() {
//        return ResponseEntity.ok("Hello, man!");
//    }
    
//    @GetMapping("/{userId}/hello")
//    public ResponseEntity<String> personalHello(@PathVariable("userId") String userId) {
//        if (userId != null)
//            return ResponseEntity.ok("Hello, " + userId);
//        else
//            return ResponseEntity.ok("Hello, man!");
//    }
    
    @GetMapping(path="/{userId}/hello", produces = "application/json")
    public String personalHello(@PathVariable("userId") String userId) {
        return "Hello, " + userId;
    }
    
    @GetMapping(path="/hello", produces = "application/json")
    public ResponseEntity<String> genericHello() {
        return ResponseEntity.ok("Hello, man!");
    }
    
    private List<RafflePersonDTO> toDTO(List<? extends RafflePerson> list) {
        List<RafflePersonDTO> result = null;
        result = new ArrayList<>();
        RafflePersonDTO dto = null;
        for (RafflePerson rp: list) {
            dto = new RafflePersonDTO();
            dto.setRafflePersonId(rp.getId());
            dto.setCanGo(rp.getCanGo());
            dto.setHaveGone(rp.getHaveGone());
            
            // Person
            dto.setPersonId(rp.getPerson().getId());
            dto.setName(rp.getPerson().getName());
//            dto.setEmail(rp.getPerson().getEmailAccount(). getEmailAccount());
//            dto.setPhoneNumber(rp.getPerson().getMobileNumber().getMobileNumber());
            dto.setPersonId(rp.getPerson().getId());
            dto.setComunidad(rp.getPerson().getComunidad());
            
            // Raffle
            dto.setRaffleId(rp.getRaffle().getId());
            
            // Service
            dto.setServiceName(rp.getRaffle().getService().getServiceName());
            dto.setServiceDate(rp.getRaffle().getDate());
            
            result.add(dto);
        }
        return result;
    }
    
    private PersonDTO toPersonDTO(Person myInfo) {
        PersonDTO dto = new PersonDTO();
        // Person
        dto.setPersonId(myInfo.getId());
        dto.setName(myInfo.getName());
//        dto.setEmail(myInfo.getEmailAccount().getEmailAccount());
//        dto.setPhoneNumber(myInfo.getMobileNumber().getMobileNumber());
        dto.setPersonId(myInfo.getId());
        
        return dto;
    }

}