package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.Feed;
import PTR.PTR.model.User;
import PTR.PTR.repository.FeedRepository;
import PTR.PTR.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedService {
    FeedRepository feedRepository;
    UserRepository userRepository;

    public FeedService(FeedRepository feedRepository, UserRepository userRepository) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
    }

    // 피드 업로드
    public Feed saveFeed(MultipartFile file, String userId, Feed feed){
        try {
            byte[] fileBytes = file.getBytes();
            User user = userRepository.findByUserId(userId).get();
            feed.setCreatedAt(LocalDateTime.now());
            feed.setUser(user);
            feed.setImageData(fileBytes);
            return feedRepository.save(feed);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 피드 읽기
    public List<Feed> getFeed(){
        return feedRepository.findAll();
    }

    //최근 피드 읽기
    public List<Feed> getRecentFeed(){
        return feedRepository.findAll().reversed();
    }

    // 유저 아이디로 피드 읽기
    public List<Feed> getFeedById(User user){
        return feedRepository.findByUser(user);

    }

    // 유저 이름으로 피드 읽기
    public List<Feed> getFeedByName(User user){
        return feedRepository.findByUser(user);
    }

    // 피드 삭제
    public void deleteFeed(Feed feed){
        feedRepository.deleteById(feed.getId());
    }

    // 피드 수정
    public Feed updateFeed(Feed feed){
        Optional<Feed> feedOptional = feedRepository.findById(feed.getId());
        if (feedOptional.isEmpty()){
            throw new ResourceNotFoundException("Feed", "Id", feed.getId());
        }else {
            Feed temp = feedOptional.get();
            feedOptional.get().setText(feed.getText());
            feedOptional.get().setImage(feed.getImage());
            feedOptional.get().setUpdateTime(LocalDateTime.now());
            feedRepository.save(temp);
            return temp;
        }
    }

    public Feed updateFeed2(MultipartFile file, Feed feed){
        try {
            Optional<Feed> feedOptional = feedRepository.findById(feed.getId());
            if (feedOptional.isEmpty()){
                throw new ResourceNotFoundException("Feed", "Id", feed.getId());
            }else {
                Feed temp = feedOptional.get();
                if(file!=null){
                    byte[] fileBytes = file.getBytes();
                    temp.setImageData(fileBytes);
                }
                temp.setText(feed.getText());
                temp.setUpdateTime(LocalDateTime.now());
                feedRepository.save(temp);
                return temp;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //피드 아이디로 피드 한 개 불러오기
    public Optional<Feed> getFeedByFeedId(Feed feed) {
        return feedRepository.findById(feed.getId());
    }

    //유저의 피드 개수 조회
    public int getNumberOfFeed(User user){
        return feedRepository.findByUser(user).size();
    }
}