package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.MiniGame1;
import PTR.PTR.model.MiniGame2;
import PTR.PTR.model.User;
import PTR.PTR.repository.MiniGame2Repository;
import PTR.PTR.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MiniGame2Service {
    MiniGame2Repository miniGame2Repository;
    UserRepository userRepository;

    public MiniGame2Service(MiniGame2Repository miniGame2Repository, UserRepository userRepository) {
        this.miniGame2Repository = miniGame2Repository;
        this.userRepository = userRepository;
    }

    // 점수 등록  //유저들을 검사해서 랭킹목록에 없으면 0점으로 기록해서 등록
    public void saveMiniGame2(){
        List<MiniGame2> miniGame2List = miniGame2Repository.findAll();
        List<User> userList = userRepository.findAll();

        // 현재 랭크된 사용자 ID를 추출하여 Set에 저장
        Set<String> rankedUserIds = miniGame2List.stream()
                .map(miniGame -> miniGame.getUser().getUserId())
                .collect(Collectors.toSet());

        for (User user : userList) {
            // 사용자가 이미 랭크되어 있는지 확인
            if (!rankedUserIds.contains(user.getUserId())) {
                MiniGame2 newMiniGame2 = new MiniGame2();
                newMiniGame2.setUser(user);
                newMiniGame2.setScore(0); // 초기 점수를 0으로 설정
                newMiniGame2.setHighScore(0); // 초기 점수를 0으로 설정
                newMiniGame2.setCreatedAt(LocalDateTime.now());
                miniGame2Repository.save(newMiniGame2);
            }
        }

    }

    // 점수 갱신
    public MiniGame2 updateMiniGame2Score(MiniGame2 miniGame2){
        Optional<MiniGame2> miniGame2Optional = miniGame2Repository.findById(miniGame2.getId());
        if (miniGame2Optional.isEmpty()){
            throw new ResourceNotFoundException("MiniGame1", "Id", miniGame2.getId());
        }else {
            MiniGame2 temp = miniGame2Optional.get();
            miniGame2Optional.get().setScore(miniGame2.getScore());
            if (miniGame2.getScore() > miniGame2.getHighScore()){
                miniGame2Optional.get().setHighScore(miniGame2.getScore());
            }
            miniGame2Optional.get().setCreatedAt(LocalDateTime.now());
            miniGame2Repository.save(temp);
            return temp;
        }
    }

    // 매주 월요일 자정에 점수 초기화
    @Scheduled(cron = "0 0 0 * * MON")
    public void resetMiniGame2Score() {
        List<MiniGame2> miniGameList = miniGame2Repository.findAll();
        for (MiniGame2 user : miniGameList) {
            user.setScore(0);
            miniGame2Repository.save(user);
        }
    }

    //전체 순위
    public List<MiniGame2> getMiniGame2Rank(){
        List<MiniGame2> miniGameList = miniGame2Repository.findAll();
        //순위 정렬 필요
        miniGameList.sort((m1, m2) -> Integer.compare(m2.getHighScore(), m1.getHighScore()));
        return miniGame2Repository.findAll();
    }

    //아이디로 등수 찾기
    public MiniGame2 getMiniGame2RankByUser(User user){
        return miniGame2Repository.findByUser(user);
    }
}
