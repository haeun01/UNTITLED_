package PTR.PTR.repository;

import PTR.PTR.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedCommentLikeRepository extends JpaRepository<FeedCommentLike,Long> {
    List<FeedCommentLike> findByFeedComment(FeedComment feedComment);

    FeedCommentLike findByFeedCommentAndUser(FeedComment feedComment, User user);

    void deleteByFeedCommentAndUser(FeedComment feedComment, User user);

    void deleteByFeedComment(FeedComment feedComment);

    List<FeedCommentLike> findAllByFeedCommentIn(List<FeedComment> feedComment);
}
