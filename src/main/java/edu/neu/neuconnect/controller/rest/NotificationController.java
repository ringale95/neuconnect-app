package edu.neu.neuconnect.controller.rest;

import edu.neu.neuconnect.controller.rest.options.PaginationOption;
import edu.neu.neuconnect.controller.rest.types.PaginationResponseType;
import edu.neu.neuconnect.dao.NotificationDAO;
import edu.neu.neuconnect.model.Notification;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationDAO notificationDAO;
    @PostMapping("/fetch")
    public ResponseEntity paginationNotification(@RequestBody PaginationOption option, HttpSession session) throws Exception {
        List<Notification> records = notificationDAO.pagination(option, (long)session.getAttribute("userId"));
        int pageCount = notificationDAO.getPageCount(option, (long)session.getAttribute("userId"));
        return ResponseEntity.status(HttpStatus.OK).body(new PaginationResponseType<Notification>(records, option.getPageNumber(), pageCount));
    }
}
