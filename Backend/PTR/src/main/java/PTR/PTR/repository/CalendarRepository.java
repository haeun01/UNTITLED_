package PTR.PTR.repository;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar,Long> {
    Calendar findByDateAndUser(LocalDate date, User user);
}
