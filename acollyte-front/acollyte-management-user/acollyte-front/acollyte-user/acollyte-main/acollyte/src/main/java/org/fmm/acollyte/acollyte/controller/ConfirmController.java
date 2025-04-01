package org.fmm.acollyte.acollyte.controller;

import org.fmm.acollyte.acollyte.dto.ConfirmDTO;
import org.fmm.acollyte.acollyte.service.AcollyteMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
//@RequestMapping("/acollyte/raffle")
public class ConfirmController {

    @Autowired
    private AcollyteMgmtService acollyteMgmtService;

//    @PatchMapping("/{userId}")
    public ResponseEntity<String> confirmAttendance(@PathVariable(name = "userId") String userId, @RequestBody ConfirmDTO confirmDTO) {
        acollyteMgmtService.confirmAttendance(confirmDTO);
        return ResponseEntity.ok("Raffle Person updated");
    }
}