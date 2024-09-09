package PTR.PTR.repository;

import PTR.PTR.model.Feed;
import PTR.PTR.model.FeedLike;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedLikeRepository extends JpaRepository<FeedLike,Long> {
    List<FeedLike> findByFeed(Feed feed);

    FeedLike findByFeedAndUser(Feed feed, User user);

    void deleteByFeedAndUser(Feed feed, User user);

    void deleteByFeed(Feed feed);

    List<FeedLike> findByUser(User user);
}
