package PTR.PTR.repository;

import PTR.PTR.model.Category;
import PTR.PTR.model.User;
import PTR.PTR.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    UserCategory findByUserAndCategory(User user, Category category);
    List<UserCategory> findByUser(User user);
}
