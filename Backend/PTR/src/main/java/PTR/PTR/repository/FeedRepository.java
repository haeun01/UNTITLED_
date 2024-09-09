package PTR.PTR.repository;

import PTR.PTR.model.Feed;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed,Long> {
    List<Feed> findByUser(User user);
}
