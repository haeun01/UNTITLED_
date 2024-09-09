package PTR.PTR.controller;

import PTR.PTR.service.NotificationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
