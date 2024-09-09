package PTR.PTR.service;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import PTR.PTR.repository.LectureRepository;
import PTR.PTR.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    TeacherRepository teacherRepository;
    LectureRepository lectureRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    public Teacher getTeacherById(long id){
        return teacherRepository.findById(id).get();
    }

    public List<Teacher> getTeacher(){
        return teacherRepository.findAll();
    }

    public Teacher userIsTeacher(User user){
        return teacherRepository.findByUser(user);
    }
}
