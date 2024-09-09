package PTR.PTR.repository;

import PTR.PTR.model.NotificationUser;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationUserRepository extends JpaRepository<NotificationUser,Long> {
    List<NotificationUser> findByUser(User user);
}
