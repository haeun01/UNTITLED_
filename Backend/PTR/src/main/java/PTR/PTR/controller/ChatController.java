package PTR.PTR.controller;

import PTR.PTR.dto.ChatMessageDto;
import PTR.PTR.model.ChatMessage;
import PTR.PTR.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessageDto chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // 사용자 인증 정보 가져오기
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            chatMessage.setSenderName(username);
        }

        // 채팅 메시지를 처리하고 브로드캐스트
        chatService.saveMessage(chatMessage);
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
