package PTR.PTR.repository;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarExercisePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarExercisePlanRepository extends JpaRepository<CalendarExercisePlan,Long> {
    CalendarExercisePlan findByCalendar(Calendar calendar);
}
