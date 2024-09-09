package PTR.PTR.controller;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureUser;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import PTR.PTR.service.LectureUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LectureUserController {
    LectureUserService lectureUserService;

    public LectureUserController(LectureUserService lectureUserService) {
        this.lectureUserService = lectureUserService;
    }
    // 강의 구매
    @PostMapping("/api/buyLecture")
    public ResponseEntity<String> buyLecture(@RequestBody LectureUser lectureUser){
        return new ResponseEntity<>(lectureUserService.buyLecture(lectureUser), HttpStatus.OK);
    }
    // 유저가 구매한 강의 내역
    @PostMapping("/api/myBuyLecture")
    public ResponseEntity<List<LectureUser>> myBuyLecture(@RequestBody User user){
        return new ResponseEntity<>(lectureUserService.myBuyLecture(user), HttpStatus.OK);
    }
    // 강의 구매자수 조회
    @PostMapping("/api/buyNumber")
    public ResponseEntity<Integer> buyNumber(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureUserService.buyNumber(lecture), HttpStatus.OK);
    }
    // 강의의 별점 및 리뷰 재작성
    @PostMapping("/api/changeLectureUser")
    public ResponseEntity<LectureUser> changeLectureUser(@RequestBody LectureUser lectureUser){
        return new ResponseEntity<>(lectureUserService.changeLectureUser(lectureUser), HttpStatus.OK);
    }
    // 별점 평균
    @PostMapping("/api/ratingAVG")
    public ResponseEntity<Float> ratingAVG(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureUserService.ratingAVG(lecture), HttpStatus.OK);
    }
    // 강의의 리뷰 및 별점 조회(리뷰를 작성한 경우만 보여줌. 별점만 작성한 경우 안 보여줌.)
    @PostMapping("/api/findReviewByLecture")
    public ResponseEntity<List<LectureUser>> findReviewByLecture(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureUserService.findReviewByLecture(lecture), HttpStatus.OK);
    }
    @PostMapping("/api/findByLectureId")
    public ResponseEntity<List<LectureUser>> findByLectureId(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureUserService.findByLectureId(lecture), HttpStatus.OK);
    }

    @GetMapping("/api/todayLectureUser")
    public ResponseEntity<List<LectureUser>> todayLectureUser(){
        return new ResponseEntity<>(lectureUserService.todayLectureUser(), HttpStatus.OK);
    }

    @GetMapping("/api/AllLectureUser")
    public ResponseEntity<List<LectureUser>> AllLectureUser(){
        return new ResponseEntity<>(lectureUserService.AllLectureUser(), HttpStatus.OK);
    }
    @PostMapping("/api/findLectureUserByTeacher")
    public ResponseEntity<List<LectureUser>> findLectureUserByTeacher(@RequestBody Teacher teacher){
        return new ResponseEntity<>(lectureUserService.findLectureUserByTeacher(teacher), HttpStatus.OK);
    }

}
