package PTR.PTR.repository;

import PTR.PTR.model.Category;
import PTR.PTR.model.Lecture;
import PTR.PTR.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByTeacher(Teacher teacher);
    List<Lecture> findByLectureNameContainingOrDescriptionContaining(String lectureName, String description);
}
