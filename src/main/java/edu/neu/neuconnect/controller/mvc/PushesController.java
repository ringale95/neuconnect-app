package edu.neu.neuconnect.controller.mvc;

import edu.neu.neuconnect.model.Notification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PushesController {

    @GetMapping("/pushes")
    public String getNotifications(){
        return "notification/notification-list";
    }
}
