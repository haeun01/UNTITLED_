package PTR.PTR.repository;

import PTR.PTR.model.Message;
import PTR.PTR.model.Status;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findByUserAndUser2(User user, User user2);
    List<Message> findByUserAndUser2AndStatus(User user, User user2, Status status);
}
