package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.MiniGame1;
import PTR.PTR.model.User;
import PTR.PTR.repository.MiniGame1Repository;
import PTR.PTR.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MiniGame1Service {
    MiniGame1Repository miniGame1Repository;
    UserRepository userRepository;

    public MiniGame1Service(MiniGame1Repository miniGame1Repository, UserRepository userRepository) {
        this.miniGame1Repository = miniGame1Repository;
        this.userRepository = userRepository;
    }

    // 점수 등록  //유저들을 검사해서 랭킹목록에 없으면 0점으로 기록해서 등록
    public void saveMiniGame1(){
        List<MiniGame1> miniGame1List = miniGame1Repository.findAll();
        List<User> userList = userRepository.findAll();

        // 현재 랭크된 사용자 ID를 추출하여 Set에 저장
        Set<String> rankedUserIds = miniGame1List.stream()
                .map(miniGame -> miniGame.getUser().getUserId())
                .collect(Collectors.toSet());

        for (User user : userList) {
            // 사용자가 이미 랭크되어 있는지 확인
            if (!rankedUserIds.contains(user.getUserId())) {
                MiniGame1 newMiniGame1 = new MiniGame1();
                newMiniGame1.setUser(user);
                newMiniGame1.setScore(0); // 초기 점수를 0으로 설정
                newMiniGame1.setHighScore(0); // 초기 점수를 0으로 설정
                newMiniGame1.setCreatedAt(LocalDateTime.now());
                miniGame1Repository.save(newMiniGame1);
            }
        }
    }

    // 점수 갱신
    public MiniGame1 updateMiniGame1Score(MiniGame1 miniGame1){
        Optional<MiniGame1> miniGame1Optional = miniGame1Repository.findById(miniGame1.getId());
        if (miniGame1Optional.isEmpty()){
            throw new ResourceNotFoundException("MiniGame1", "Id", miniGame1.getId());
        }else {
            MiniGame1 temp = miniGame1Optional.get();
            miniGame1Optional.get().setScore(miniGame1.getScore());
            if (miniGame1.getScore() > miniGame1.getHighScore()){
                miniGame1Optional.get().setHighScore(miniGame1.getScore());
            }
            miniGame1Optional.get().setCreatedAt(LocalDateTime.now());
            miniGame1Repository.save(temp);
            return temp;
        }
    }

    // 매주 월요일 자정에 점수 초기화
    @Scheduled(cron = "0 0 0 * * MON")
    public void resetMiniGame1Score() {
        List<MiniGame1> miniGameList = miniGame1Repository.findAll();
        for (MiniGame1 user : miniGameList) {
            user.setScore(0);
            miniGame1Repository.save(user);
        }
    }

    //전체 순위
    public List<MiniGame1> getMiniGame1Rank(){
        List<MiniGame1> miniGameList = miniGame1Repository.findAll();
        //순위 정렬 필요
        miniGameList.sort((m1, m2) -> Integer.compare(m2.getHighScore(), m1.getHighScore()));
        return miniGame1Repository.findAll();
    }

    //아이디로 등수 찾기
    public MiniGame1 getMiniGame1RankByUser(User user){
        return miniGame1Repository.findByUser(user);
    }
}
