package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.Calendar;
import PTR.PTR.model.User;
import PTR.PTR.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarService {
    CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }
    //작성
    public Calendar saveCalendar(Calendar calendar){
        return calendarRepository.save(calendar);
    }

    //읽기
    public Optional<Calendar> getCalendar(Calendar calendar){
        return calendarRepository.findById(calendar.getId());
    }

    //삭제
    public void deleteCalendar(Calendar calendar){
        calendarRepository.deleteById(calendar.getId());
    }

    //수정
    public Calendar updateCalendar(Calendar calendar){
        Optional<Calendar> calendarOptional = calendarRepository.findById(calendar.getId());
        if (calendarOptional.isEmpty()){
            throw new ResourceNotFoundException("Calendar", "Id", calendar.getId());
        }else {
            Calendar temp = calendarOptional.get();
            calendarOptional.get().setWeightGoal(calendar.getWeightGoal());
            calendarOptional.get().setWeightRecord(calendar.getWeightRecord());
            calendarOptional.get().setWaterIntake(calendar.getWaterIntake());
            calendarOptional.get().setText(calendar.getText());
            calendarRepository.save(temp);
            return temp;
        }
    }

    public List<Calendar> findCalendarDay5(User user){
        LocalDate now = LocalDate.now();
        List<Calendar> calendars = new ArrayList<>();
        calendars.add(calendarRepository.findByDateAndUser(now, user));
        for (int i=1; i<5; i++){
            now = now.minusDays(1);
            calendars.add(calendarRepository.findByDateAndUser(now, user));
        }
        return calendars;
    }

    public List<Calendar> findCalenderMonth(Calendar calendar){
        return calendarRepository.findAll().stream()
                .filter(c -> c.getDate().getYear() == calendar.getDate().getYear()
                        && c.getDate().getMonth() == calendar.getDate().getMonth()
                        && c.getUser().getUserId().equals(calendar.getUser().getUserId())).collect(Collectors.toList());
    }


}
