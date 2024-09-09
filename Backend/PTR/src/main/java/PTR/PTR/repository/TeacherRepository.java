package PTR.PTR.repository;

import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Teacher findByUser(User user);
}
