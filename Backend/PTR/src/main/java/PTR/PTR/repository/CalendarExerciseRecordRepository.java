package PTR.PTR.repository;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarExerciseRecordRepository extends JpaRepository<CalendarExerciseRecord,Long> {
    CalendarExerciseRecord findByCalendar(Calendar calendar);
}
