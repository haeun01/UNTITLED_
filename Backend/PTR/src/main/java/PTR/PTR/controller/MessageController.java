package PTR.PTR.controller;

import PTR.PTR.model.Message;
import PTR.PTR.model.User;
import PTR.PTR.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    // 메시지 생성
    @PostMapping("/api/createMessage")
    public ResponseEntity<String> createMessage(@RequestBody Message message){
        return new ResponseEntity<>(messageService.createMessage(message), HttpStatus.OK);
    }
    // 메시지 보기
    @PostMapping("/api/viewMessage")
    public ResponseEntity<List<Message>> viewMessage(@RequestBody List<User> users){
        User user = users.get(0);
        User user2 = users.get(1);
        return new ResponseEntity<>(messageService.viewMessage(user, user2), HttpStatus.OK);
    }
}
