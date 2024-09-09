package PTR.PTR.controller;

import PTR.PTR.model.Feed;
import PTR.PTR.model.User;
import PTR.PTR.service.FeedService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class FeedController {
    FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @PostMapping("/api/feed")
    public String saveFeed(@RequestParam("file") MultipartFile file, @RequestParam("user") String userId, @ModelAttribute Feed feed){
        feedService.saveFeed(file, userId, feed);
        return "정상작동";
    }

    //피드 전체 조회
    @GetMapping("/api/feed")
    public List<Feed> getFeed(){
        return feedService.getFeed();
    }
    //피드 아이디로 피드 한개 조회
    @PostMapping("/api/feed/feed")
    public Optional<Feed> getFeedByFeedId(@RequestBody Feed feed){
        return feedService.getFeedByFeedId(feed);
    }
    @GetMapping("/api/getRecentFeed")
    public List<Feed> getRecentFeed(){
        return feedService.getRecentFeed();
    }

    @PostMapping("/api/feed/id")
    public List<Feed> getFeedById(@RequestBody User user){
        return feedService.getFeedById(user);
    }

    @PostMapping("/api/feed/name")
    public List<Feed> getFeedByName(@RequestBody User user){
        return feedService.getFeedByName(user);
    }

    @PostMapping("/api/deleteFeed")
    public void deleteFeed(@RequestBody Feed feed){
        feedService.deleteFeed(feed);
    }

    @PutMapping("/api/feed")
    public Feed updateFeed(@RequestBody Feed feed){
        return feedService.updateFeed(feed);
    }

    @PostMapping("/api/numberOfFeed")
    public int getNumberOfFeed(@RequestBody User user){
        return feedService.getNumberOfFeed(user);
    }

    @PostMapping("/api/updateFeed")
    public String updateFeed2(@RequestParam(value = "file", required = false) MultipartFile file, @ModelAttribute Feed feed){
        feedService.updateFeed2(file, feed);
        return "정상작동";
    }
}
