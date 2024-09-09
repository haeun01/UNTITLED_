package PTR.PTR.repository;

import PTR.PTR.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    Subscription findByUserAndTeacher(User user, Teacher teacher);

    List<Subscription> findByUser(User user);
    List<Subscription> findByTeacher(Teacher teacher);
}
