package PTR.PTR.controller;

import PTR.PTR.model.MessageBlock;
import PTR.PTR.model.User;
import PTR.PTR.service.MessageBlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MessageBlockController {
    MessageBlockService messageBlockService;

    public MessageBlockController(MessageBlockService messageBlockService) {
        this.messageBlockService = messageBlockService;
    }
    // 차단
    @PostMapping("/api/messageBlock")
    public ResponseEntity<MessageBlock> messageBlock(@RequestBody MessageBlock messageBlock){
        return new ResponseEntity<>(messageBlockService.messageBlock(messageBlock), HttpStatus.OK);
    }
    // 내가 차단한 유저 목록
    @PostMapping("/api/myMessageBlock")
    public ResponseEntity<List<MessageBlock>> myMessageBlock(@RequestBody User user){
        return new ResponseEntity<>(messageBlockService.myMessageBlock(user), HttpStatus.OK);
    }
}
