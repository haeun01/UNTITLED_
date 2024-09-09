package PTR.PTR.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/api/ptr/community.html")
    public String community(){
        return "community";
    }
    @GetMapping("/api/ptr/customerService.html")
    public String customerService(){
        return "customerService";
    }
    @GetMapping("/api/ptr/feed.html")
    public String feed(){
        return "feed";
    }
    @GetMapping("/api/ptr/lecture.html")
    public String lecture(){
        return "lecture";
    }
    @GetMapping("/api/ptr/login.html")
    public String login(){
        return "login";
    }
    @GetMapping("/api/ptr/main.html")
    public String mainPage(){
        return "main";
    }
    @GetMapping("/api/ptr/management.html")
    public String management(){
        return "management";
    }
    @GetMapping("/api/ptr/manager.html")
    public String manager(){
        return "manager";
    }
    @GetMapping("/api/ptr/mypage.html")
    public String mypage(){
        return "mypage";
    }
    @GetMapping("/api/ptr/record.html")
    public String record(){
        return "record";
    }
    @GetMapping("/api/ptr/recordExercise.html")
    public String recordExercise(){
        return "recordExercise";
    }
    @GetMapping("/api/ptr/recordFood.html")
    public String recordFood(){
        return "recordFood";
    }
    @GetMapping("/api/ptr/signUp.html")
    public String signUp(){
        return "signUp";
    }
    @GetMapping("/api/ptr/teacher.html")
    public String teacher(){
        return "teacher";
    }
    @GetMapping("/api/ptr/lectureView.html")
    public String lectureView(){
        return "lectureView";
    }
    @GetMapping("/api/ptr/teacherView.html")
    public String teacherView(){
        return "teacherView";
    }
    @GetMapping("/api/index.html")
    public String index(){
        return "index";
    }
}
