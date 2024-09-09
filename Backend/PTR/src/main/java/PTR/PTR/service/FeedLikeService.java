package PTR.PTR.service;

import PTR.PTR.model.Feed;
import PTR.PTR.model.FeedComment;
import PTR.PTR.model.FeedLike;
import PTR.PTR.model.User;
import PTR.PTR.repository.FeedLikeRepository;
import PTR.PTR.repository.FeedRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedLikeService {
    FeedLikeRepository feedLikeRepository;
    FeedRepository feedRepository;

    public FeedLikeService(FeedLikeRepository feedLikeRepository, FeedRepository feedRepository) {
        this.feedLikeRepository = feedLikeRepository;
        this.feedRepository = feedRepository;
    }

    //좋아요
    public FeedLike feedLike(FeedLike feedLike){
        feedLike.setCreatedAt(LocalDateTime.now());
        return feedLikeRepository.save(feedLike);
    }

    //좋아요 취소
    @Transactional
    public void deleteFeedLike(FeedLike feedLike){
        feedLikeRepository.deleteByFeedAndUser(feedLike.getFeed(), feedLike.getUser());
    }
    @Transactional
    public void deleteFeedLikeByFeed(Feed feed) {
        feedLikeRepository.deleteByFeed(feed);
    }

    //좋아요 확인
    public List<FeedLike> getFeedLike(Feed feed){
        return feedLikeRepository.findByFeed(feed);
    }

    //좋아요 개수
    public int getNumberOfFeedLike(Feed feed){
        return feedLikeRepository.findByFeed(feed).size();
    }

    //유저가 피드 좋아요 눌렀는지 확인
    public boolean checkFeedLikeClick(FeedLike feedLike){
        return feedLikeRepository.findByFeedAndUser(feedLike.getFeed(), feedLike.getUser()) != null;
    }

    public List<FeedLike> getFeedLikeByUser(User user){
        return feedLikeRepository.findByUser(user);
    }
}
