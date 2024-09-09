package PTR.PTR.repository;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureScrap;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureScrapRepository extends JpaRepository<LectureScrap, Long> {
    List<LectureScrap> findByUser(User user);
    LectureScrap findByUserAndLecture(User user, Lecture lecture);
}
