package PTR.PTR.service;

import PTR.PTR.model.Feed;
import PTR.PTR.model.FeedComment;
import PTR.PTR.model.FeedCommentLike;
import PTR.PTR.model.FeedLike;
import PTR.PTR.repository.FeedCommentLikeRepository;
import PTR.PTR.repository.FeedCommentRepository;
import PTR.PTR.repository.FeedRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedCommentLikeService {
    FeedCommentLikeRepository feedCommentLikeRepository;
    FeedCommentRepository feedCommentRepository;
    FeedRepository feedRepository;

    public FeedCommentLikeService(FeedCommentLikeRepository feedCommentLikeRepository, FeedCommentRepository feedCommentRepository) {
        this.feedCommentLikeRepository = feedCommentLikeRepository;
        this.feedCommentRepository = feedCommentRepository;
    }

    //좋아요(아이디 코멘트 유저
    public FeedCommentLike feedCommentLike(FeedCommentLike feedCommentLike){
        return feedCommentLikeRepository.save(feedCommentLike);
    }


    //좋아요 취소
    @Transactional
    public void deleteFeedCommentLike(FeedCommentLike feedCommentLike){
        feedCommentLikeRepository.deleteByFeedCommentAndUser(feedCommentLike.getFeedComment(), feedCommentLike.getUser());
    }
    @Transactional
    public void deleteFeedCommentLikeByFeedComment(FeedComment feedComment) {
        feedCommentLikeRepository.deleteByFeedComment(feedComment);
    }
    @Transactional
    public void deleteFeedCommentLikeByFeed(Feed feed){
        List<FeedComment> feedComments = feedCommentRepository.findByFeed(feed);
        List<FeedCommentLike> removes = feedCommentLikeRepository.findAllByFeedCommentIn(feedComments);
        feedCommentLikeRepository.deleteAll(removes);
    }

    //좋아요 확인
    public List<FeedCommentLike> getFeedCommentLike(FeedComment feedComment){
        return feedCommentLikeRepository.findByFeedComment(feedComment);
    }

    //유저가 댓글 좋아요 눌렀는지 확인
    public boolean checkFeedCommentLikeClick(FeedCommentLike feedCommentLike) {
        return feedCommentLikeRepository.findByFeedCommentAndUser(feedCommentLike.getFeedComment(), feedCommentLike.getUser()) != null;
    }

    //좋아요 개수
    public int getNumberOfFeedCommentLike(FeedComment feedComment){
        return feedCommentLikeRepository.findByFeedComment(feedComment).size();
    }

}
