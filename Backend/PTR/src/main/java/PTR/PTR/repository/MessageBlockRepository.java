package PTR.PTR.repository;

import PTR.PTR.model.MessageBlock;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageBlockRepository extends JpaRepository<MessageBlock,Long> {
    List<MessageBlock> findByUser(User user);
    List<MessageBlock> findByUserAndUser2(User user, User user2);
}
