package PTR.PTR.repository;

import PTR.PTR.model.MiniGame1;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MiniGame1Repository extends JpaRepository<MiniGame1,Long> {
    MiniGame1 findByUser(User user);

}
