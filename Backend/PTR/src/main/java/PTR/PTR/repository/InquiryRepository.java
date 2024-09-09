package PTR.PTR.repository;

import PTR.PTR.model.Inquiry;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry,Long> {
    List<Inquiry> findByUser(User user);
}
