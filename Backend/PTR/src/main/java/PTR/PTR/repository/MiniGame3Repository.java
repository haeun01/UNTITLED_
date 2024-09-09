package PTR.PTR.repository;

import PTR.PTR.model.MiniGame3;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiniGame3Repository extends JpaRepository<MiniGame3,Long> {
    MiniGame3 findByUser(User user);
}
