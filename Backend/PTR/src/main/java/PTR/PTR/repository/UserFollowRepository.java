package PTR.PTR.repository;

import PTR.PTR.model.User;
import PTR.PTR.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowRepository extends JpaRepository<UserFollow,Long> {
    List<UserFollow> findByUser(User user);

    List<UserFollow> findByUser2(User user);
}
