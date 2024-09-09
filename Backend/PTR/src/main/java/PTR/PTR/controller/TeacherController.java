package PTR.PTR.controller;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import PTR.PTR.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/api/teacher/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable long id) {
        return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
    }

    @GetMapping("/api/teacher")
    public ResponseEntity<List<Teacher>> getTeacher() {
        return new ResponseEntity<>(teacherService.getTeacher(), HttpStatus.OK);
    }

    @PostMapping("/api/userIsTeacher")
    public ResponseEntity<Teacher> userIsTeacher(@RequestBody User user){
        return new ResponseEntity<>(teacherService.userIsTeacher(user), HttpStatus.OK);
    }
}
