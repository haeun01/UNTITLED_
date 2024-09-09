package PTR.PTR.controller;

import PTR.PTR.model.Feed;
import PTR.PTR.model.FeedComment;
import PTR.PTR.model.User;
import PTR.PTR.service.FeedCommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedCommentController {
    FeedCommentService feedCommentService;

    public FeedCommentController(FeedCommentService feedCommentService) {
        this.feedCommentService = feedCommentService;
    }

    @PostMapping("/api/feedComment")
    public String saveFeedComment(@RequestBody FeedComment feedComment){
        feedCommentService.saveFeedComment(feedComment);
        return "정상";
    }
    @PostMapping("/api/getFeedComment")
    public List<FeedComment> getFeedComment(@RequestBody Feed feed){
        return feedCommentService.getFeedComment(feed);
    }


    @PostMapping("/api/deleteFeedComment")
    public void deleteFeedComment(@RequestBody FeedComment feedComment){
        feedCommentService.deleteFeedComment(feedComment);
    }
    @PutMapping("/api/feedComment")
    public FeedComment updateFeedComment(@RequestBody FeedComment feedComment){
        return feedCommentService.updateFeedComment(feedComment);
    }

    @PostMapping("/api/numberOfFeedComment")
    public int getNumberOfFeedComment(@RequestBody Feed feed){
        return feedCommentService.getNumberOfFeedComment(feed);
    }

    @PostMapping("/api/deleteFeedCommentByFeed")
    public void deleteFeedCommentByFeed(@RequestBody Feed feed){
        feedCommentService.deleteFeedCommentByFeed(feed);
    }

    @PostMapping("/api/getFeedCommentByFeedComment")
    public List<FeedComment> getFeedCommentByFeedComment(@RequestBody FeedComment feedComment){
        return feedCommentService.getFeedCommentByFeedComment(feedComment);
    }

    @PostMapping("/api/getFeedCommentByUser")
    public List<FeedComment> getFeedCommentByUser(@RequestBody User user){
        return feedCommentService.getFeedCommentByUser(user);
    }
}
