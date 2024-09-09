package PTR.PTR.controller;

import PTR.PTR.model.Feed;
import PTR.PTR.model.FeedComment;
import PTR.PTR.model.FeedLike;
import PTR.PTR.model.User;
import PTR.PTR.service.FeedLikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedLikeController {
    FeedLikeService feedLikeService;

    public FeedLikeController(FeedLikeService feedLikeService) {
        this.feedLikeService = feedLikeService;
    }

    @PostMapping("/api/feedLike")
    public String feedLike(@RequestBody FeedLike feedLike){
        feedLikeService.feedLike(feedLike);
        return "정상작동";
    }

    @DeleteMapping("/api/feedLike")
    public void deleteFeedLike(@RequestBody FeedLike feedLike){
        feedLikeService.deleteFeedLike(feedLike);
    }
    @PostMapping("/api/deleteFeedLikeByFeed")
    public void deleteFeedLikeByFeed(@RequestBody Feed feed){
        feedLikeService.deleteFeedLikeByFeed(feed);
    }

    @PostMapping("/api/getFeedLike")
    public List<FeedLike> getFeedLike(@RequestBody Feed feed){
        return feedLikeService.getFeedLike(feed);
    }

    @PostMapping("/api/numberOfFeedLike")
    public int getNumberOfFeedLike(@RequestBody Feed feed){
        return feedLikeService.getNumberOfFeedLike(feed);
    }

    @PostMapping("/api/checkFeedLikeClick")
    public boolean checkFeedLikeClick(@RequestBody FeedLike feedLike){
        return feedLikeService.checkFeedLikeClick(feedLike);
    }
    @PostMapping("/api/getFeedLikeByUser")
    public List<FeedLike> getFeedLikeByUser(@RequestBody User user){
        return feedLikeService.getFeedLikeByUser(user);
    }
}
