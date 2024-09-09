package PTR.PTR.service;

import PTR.PTR.model.MessageBlock;
import PTR.PTR.model.User;
import PTR.PTR.repository.MessageBlockRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageBlockService {
    MessageBlockRepository messageBlockRepository;

    public MessageBlockService(MessageBlockRepository messageBlockRepository) {
        this.messageBlockRepository = messageBlockRepository;
    }

    public MessageBlock messageBlock(MessageBlock messageBlock){
        messageBlock.setCreatedAt(LocalDateTime.now());
        return messageBlockRepository.save(messageBlock);
    }
    public List<MessageBlock> myMessageBlock(User user){
        return messageBlockRepository.findByUser(user);
    }
}
