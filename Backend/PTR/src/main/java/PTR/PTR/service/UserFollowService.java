package PTR.PTR.service;

import PTR.PTR.model.User;
import PTR.PTR.model.UserFollow;
import PTR.PTR.repository.UserFollowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserFollowService {
    UserFollowRepository userFollowRepository;

    public UserFollowService(UserFollowRepository userFollowRepository) {
        this.userFollowRepository = userFollowRepository;
    }

    //유저 팔로우
    public String saveUserFollow(UserFollow userFollow){
        userFollow.setCreatedAt(LocalDateTime.now());
        userFollowRepository.save(userFollow);
        return "정상 작동됨";
    }

    //팔로우 취소
    public void deleteUserFollow(UserFollow userFollow){
        userFollowRepository.deleteById(userFollow.getId());
    }

    //팔로워 조회
    public List<UserFollow> getUserFollowByUser(User user){
        return userFollowRepository.findByUser(user);
    }
    //팔로워수 조회
    public int getNumberOfFollowByUser(User user){
        return userFollowRepository.findByUser(user).size();
    }

    //팔로잉 조회
    public List<UserFollow> getUserFollowByUser2(User user){
        return userFollowRepository.findByUser2(user);
    }

    //팔로잉수 조회
    public int getNumberOfFollowByUser2(User user){
        return userFollowRepository.findByUser2(user).size();
    }

}
