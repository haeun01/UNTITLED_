package PTR.PTR.repository;

import PTR.PTR.model.MiniGame2;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiniGame2Repository extends JpaRepository<MiniGame2,Long> {
    MiniGame2 findByUser(User user);
}
