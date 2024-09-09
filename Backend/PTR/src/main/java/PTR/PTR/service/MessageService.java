package PTR.PTR.service;

import PTR.PTR.model.Message;
import PTR.PTR.model.Status;
import PTR.PTR.model.User;
import PTR.PTR.repository.MessageBlockRepository;
import PTR.PTR.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    MessageRepository messageRepository;
    MessageBlockRepository messageBlockRepository;

    public MessageService(MessageRepository messageRepository, MessageBlockRepository messageBlockRepository) {
        this.messageRepository = messageRepository;
        this.messageBlockRepository = messageBlockRepository;
    }

    public String createMessage(Message message){
        if (messageBlockRepository.findByUserAndUser2(message.getUser(), message.getUser2()).isEmpty()
                && messageBlockRepository.findByUserAndUser2(message.getUser2(), message.getUser()).isEmpty()){
            message.setCreatedAt(LocalDateTime.now());
            message.setStatus(Status.UNCONFIRMED);
            messageRepository.save(message);
            return "정상 처리됨";
        }
        return "차단";
    }

    public List<Message> viewMessage(User user, User user2){
        List<Message> messages = messageRepository.findByUserAndUser2(user, user2);
        if (!messageRepository.findByUserAndUser2AndStatus(user2, user, Status.UNCONFIRMED).isEmpty()){
            List<Message> status = messageRepository.findByUserAndUser2AndStatus(user2, user, Status.UNCONFIRMED);
            for (int i=0; i<status.size(); i++){
                status.get(i).setStatus(Status.CONFIRM);
            }
            messageRepository.saveAll(status);
        }
        messages.addAll(messageRepository.findByUserAndUser2(user2, user));
        return messages.stream().sorted(Comparator.comparingLong(Message::getId)).collect(Collectors.toList());
    }
}
