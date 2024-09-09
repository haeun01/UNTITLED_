package PTR.PTR.repository;

import PTR.PTR.model.Feed;
import PTR.PTR.model.FeedComment;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedCommentRepository extends JpaRepository<FeedComment,Long> {
    List<FeedComment> findByFeed(Feed feed);
    List<FeedComment> findByFeedComment(FeedComment feedComment);
    List<FeedComment> findByUser(User user);
    void deleteByFeed(Feed feed);
}
