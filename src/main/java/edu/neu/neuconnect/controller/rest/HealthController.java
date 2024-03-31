package edu.neu.neuconnect.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/healthz")
public class HealthController {

    //private final HealthDAO healthDAO;
    @GetMapping
    public ResponseEntity<?> healthz() {
        boolean isConnected = true;
                //healthDAO.checkDatabaseConnection();
        if (isConnected ) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.HEAD})
    public ResponseEntity<?> handleOptionsAndHead() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
