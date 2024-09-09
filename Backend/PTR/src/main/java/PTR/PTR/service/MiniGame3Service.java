package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.MiniGame1;
import PTR.PTR.model.MiniGame3;
import PTR.PTR.model.User;
import PTR.PTR.repository.MiniGame3Repository;
import PTR.PTR.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MiniGame3Service {
    MiniGame3Repository miniGame3Repository;
    UserRepository userRepository;

    public MiniGame3Service(MiniGame3Repository miniGame3Repository, UserRepository userRepository) {
        this.miniGame3Repository = miniGame3Repository;
        this.userRepository = userRepository;
    }

    // 점수 등록  //유저들을 검사해서 랭킹목록에 없으면 0점으로 기록해서 등록
    public void saveMiniGame3(){
        List<MiniGame3> miniGame3List = miniGame3Repository.findAll();
        List<User> userList = userRepository.findAll();

        // 현재 랭크된 사용자 ID를 추출하여 Set에 저장
        Set<String> rankedUserIds = miniGame3List.stream()
                .map(miniGame -> miniGame.getUser().getUserId())
                .collect(Collectors.toSet());

        for (User user : userList) {
            // 사용자가 이미 랭크되어 있는지 확인
            if (!rankedUserIds.contains(user.getUserId())) {
                MiniGame3 newMiniGame3 = new MiniGame3();
                newMiniGame3.setUser(user);
                newMiniGame3.setScore(0); // 초기 점수를 0으로 설정
                newMiniGame3.setHighScore(0); // 초기 점수를 0으로 설정
                newMiniGame3.setCreatedAt(LocalDateTime.now());
                miniGame3Repository.save(newMiniGame3);
            }
        }

    }

    // 점수 갱신
    public MiniGame3 updateMiniGame3Score(MiniGame3 miniGame3){
        Optional<MiniGame3> miniGame3Optional = miniGame3Repository.findById(miniGame3.getId());
        if (miniGame3Optional.isEmpty()){
            throw new ResourceNotFoundException("MiniGame1", "Id", miniGame3.getId());
        }else {
            MiniGame3 temp = miniGame3Optional.get();
            miniGame3Optional.get().setScore(miniGame3.getScore());
            if (miniGame3.getScore() > miniGame3.getHighScore()){
                miniGame3Optional.get().setHighScore(miniGame3.getScore());
            }
            miniGame3Optional.get().setCreatedAt(LocalDateTime.now());
            miniGame3Repository.save(temp);
            return temp;
        }
    }

    // 매주 월요일 자정에 점수 초기화
    @Scheduled(cron = "0 0 0 * * MON")
    public void resetMiniGame3Score() {
        List<MiniGame3> miniGameList = miniGame3Repository.findAll();
        for (MiniGame3 user : miniGameList) {
            user.setScore(0);
            miniGame3Repository.save(user);
        }
    }

    //전체 순위
    public List<MiniGame3> getMiniGame3Rank(){
        List<MiniGame3> miniGameList = miniGame3Repository.findAll();
        //순위 정렬 필요
        miniGameList.sort((m1, m2) -> Integer.compare(m2.getHighScore(), m1.getHighScore()));
        return miniGame3Repository.findAll();
    }

    //아이디로 등수 찾기
    public MiniGame3 getMiniGame3RankByUser(User user){
        return miniGame3Repository.findByUser(user);
    }
}
