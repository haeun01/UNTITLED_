package PTR.PTR.repository;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureUser;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureUserRepository extends JpaRepository<LectureUser,Long> {
    LectureUser findByUserAndLecture(User user, Lecture lecture);
    List<LectureUser> findByUser(User user);
    List<LectureUser> findByLecture(Lecture lecture);
    List<LectureUser> findAllByLectureIn(List<Lecture> lectures);
}
