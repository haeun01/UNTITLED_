package PTR.PTR.controller;

import PTR.PTR.model.Notification;
import PTR.PTR.model.NotificationUser;
import PTR.PTR.model.User;
import PTR.PTR.service.NotificationUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationUserController {
    NotificationUserService notificationUserService;

    public NotificationUserController(NotificationUserService notificationUserService) {
        this.notificationUserService = notificationUserService;
    }
    // 유저 알람 확인
    @PostMapping("/api/myNotification")
    public ResponseEntity<List<NotificationUser>> myNotification(@RequestBody User user){
        return new ResponseEntity<>(notificationUserService.myNotification(user), HttpStatus.OK);
    }
    // 유저 알람 삭제
    @PostMapping("/api/deleteNotification")
    public ResponseEntity<String> deleteNotification(@RequestBody NotificationUser notificationUser){
        return new ResponseEntity<>(notificationUserService.deleteNotification(notificationUser), HttpStatus.OK);
    }
    // 유저 알람 확인(읽음 처리)
    @PostMapping("/api/confirmNotification")
    public ResponseEntity<NotificationUser> confirmNotification(@RequestBody NotificationUser notificationUser){
        return new ResponseEntity<>(notificationUserService.confirmNotification(notificationUser), HttpStatus.OK);
    }
    // 유저 알람 미확인(안 읽음 처리)
    @PostMapping("/api/unconfirmedNotification")
    public ResponseEntity<NotificationUser> unconfirmedNotification(@RequestBody NotificationUser notificationUser){
        return new ResponseEntity<>(notificationUserService.unconfirmedNotification(notificationUser), HttpStatus.OK);
    }
}
