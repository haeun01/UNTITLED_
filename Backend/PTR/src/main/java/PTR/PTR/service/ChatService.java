package PTR.PTR.service;

import PTR.PTR.dto.ChatMessageDto;
import PTR.PTR.model.ChatMessage;
import PTR.PTR.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void saveMessage(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(chatMessageDto.getContent());
        chatMessage.setSenderId(chatMessageDto.getSenderId());
        chatMessage.setSenderName(chatMessageDto.getSenderName());

        chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getChatHistory(String lectureId) {
        return chatMessageRepository.findByLectureId(lectureId);
    }
}
