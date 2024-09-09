package PTR.PTR.controller;

import PTR.PTR.model.*;
import PTR.PTR.service.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class LectureController {
    LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }
    // 강의 생성
    @PostMapping("/api/createLecture")
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureService.createLecture(lecture), HttpStatus.OK);
    }
    // 강사의 강의 조회
    @PostMapping("/api/findTeacherLecture")
    public ResponseEntity<List<Lecture>> findTeacherLecture(@RequestBody Teacher teacher){
        return new ResponseEntity<>(lectureService.findTeacherLecture(teacher), HttpStatus.OK);
    }

    @PostMapping("/api/findTeacherLectureReversed")
    public ResponseEntity<List<Lecture>> findTeacherLectureReversed(@RequestBody Teacher teacher){
        return new ResponseEntity<>(lectureService.findTeacherLectureReversed(teacher), HttpStatus.OK);
    }

    // 강의 검색
    @GetMapping("/api/searchLecture")
    public List<Lecture> searchLectures(@RequestParam String query) {
        return lectureService.searchLecture(query);
    }

    // 가격으로 강의 조회
    @PostMapping("/api/findPriceLecture")
    public ResponseEntity<List<Lecture>> findPriceLecture(@RequestBody List<Integer> price){
        int minPrice = price.get(0);
        int maxPrice = price.get(1);
        return new ResponseEntity<>(lectureService.findPriceLecture(minPrice, maxPrice), HttpStatus.OK);
    }
    // 강의 카테고리 수정 및 저장
    @PostMapping("/api/saveLectureCategory")
    public ResponseEntity<String> saveLectureCategory(@RequestBody List<LectureCategory> lectureCategories){
        return new ResponseEntity<>(lectureService.saveLectureCategory(lectureCategories), HttpStatus.OK);
    }
    // 강의 카테고리 삭제
    @PostMapping("/api/deleteLecturerCategory")
    public ResponseEntity<String> deleteLecturerCategory(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureService.deleteLecturerCategory(lecture), HttpStatus.OK);
    }
    // 강의의 카테고리 조회
    @PostMapping("/api/findLectureCategory")
    public ResponseEntity<List<LectureCategory>> findLectureCategory(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureService.findLectureCategory(lecture), HttpStatus.OK);
    }
    // 카테고리로 강의 찾기
    @PostMapping("/api/findLectureByCategory")
    public ResponseEntity<List<Lecture>> findLectureByCategory(@RequestBody Category category){
        return new ResponseEntity<>(lectureService.findLectureByCategory(category), HttpStatus.OK);
    }
    @PostMapping("/api/findLectureAllByCategoryIn")
    public ResponseEntity<List<Lecture>> findLectureAllByCategoryIn(@RequestBody List<Category> categories){
        return new ResponseEntity<>(lectureService.findLectureAllByCategoryIn(categories), HttpStatus.OK);
    }
    @GetMapping("/api/findAllLecture")
    public ResponseEntity<List<Lecture>> findAllLecture(){
        return new ResponseEntity<>(lectureService.findAllLecture(),HttpStatus.OK);
    }

    @GetMapping("/api/lecture/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable long id) {
        return new ResponseEntity<>(lectureService.getLectureById(id),
                HttpStatus.OK);
    }

    @GetMapping("/api/todayLecture")
    public ResponseEntity<List<Lecture>> todayLecture(){
        return new ResponseEntity<>(lectureService.todayLecture(), HttpStatus.OK);
    }

    // 강사명 검색
    @GetMapping("/api/searchTeacher")
    public List<Lecture> searchLecturesByTeacher(@RequestParam String teacherName) {
        return lectureService.searchLectureByTeacherName(teacherName);
    }
}
