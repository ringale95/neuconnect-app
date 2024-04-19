package edu.neu.neuconnect.controller.rest;


import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.types.PaginationResponseType;
import edu.neu.neuconnect.dao.NotificationDAO;
import edu.neu.neuconnect.dao.ServiceDAO;
import edu.neu.neuconnect.model.Notification;
import edu.neu.neuconnect.model.ServiceRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private ServiceDAO serviceDAO;
    @PostMapping("/fetch")
    public ResponseEntity serviceNotification(@RequestBody PaginationOption option, HttpSession session) throws Exception {
        List<ServiceRequest> requests = serviceDAO.pagination(option, (long)session.getAttribute("userId"));
        int pageCount = serviceDAO.getPageCount(option, (long)session.getAttribute("userId"));
        return ResponseEntity.status(HttpStatus.OK).body(new PaginationResponseType<ServiceRequest>(requests, option.getPageNumber(), pageCount));
    }

}
