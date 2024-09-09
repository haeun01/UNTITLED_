package PTR.PTR.controller;

import PTR.PTR.model.MiniGame1;
import PTR.PTR.model.User;
import PTR.PTR.service.MiniGame1Service;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class MiniGame1Controller {
    MiniGame1Service miniGame1Service;

    public MiniGame1Controller(MiniGame1Service miniGame1Service) {
        this.miniGame1Service = miniGame1Service;
    }

    @PostMapping("/api/saveMiniGame1")
    public void saveMiniGame1(){
        miniGame1Service.saveMiniGame1();
    }

    // 점수 갱신
    @PutMapping("/api/updateMiniGame1")
    public MiniGame1 updateMiniGame1Score(@RequestBody MiniGame1 miniGame1){
        return miniGame1Service.updateMiniGame1Score(miniGame1);
    }

    //전체 순위
    @GetMapping("/api/miniGame1/rank")
    public List<MiniGame1> getMiniGame1Rank(){
        return miniGame1Service.getMiniGame1Rank();
    }

    //아이디로 등수 찾기
    @GetMapping("/api/miniGame1/user")
    public MiniGame1 getMiniGame1RankByUser(User user){
        return miniGame1Service.getMiniGame1RankByUser(user);
    }


}
