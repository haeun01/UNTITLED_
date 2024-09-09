package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarExercisePlan;
import PTR.PTR.repository.CalendarExercisePlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarExercisePlanService {
    CalendarExercisePlanRepository calendarExercisePlanRepository;

    public CalendarExercisePlanService(CalendarExercisePlanRepository calendarExercisePlanRepository) {
        this.calendarExercisePlanRepository = calendarExercisePlanRepository;
    }

    //작성
    public CalendarExercisePlan saveCalendarExercisePlan(CalendarExercisePlan calendarExercisePlan){
        return calendarExercisePlanRepository.save(calendarExercisePlan);
    }

    //읽기
    public Optional<CalendarExercisePlan> getCalendarExercisePlan(CalendarExercisePlan calendarExercisePlan){
        return calendarExercisePlanRepository.findById(calendarExercisePlan.getId());
    }

    //삭제
    public void deleteCalendarExercisePlan(CalendarExercisePlan calendarExercisePlan){
        calendarExercisePlanRepository.deleteById(calendarExercisePlan.getId());
    }

    //수정
    public CalendarExercisePlan updateCalendarExercisePlan(CalendarExercisePlan calendarExercisePlan){
        Optional<CalendarExercisePlan> calendarExercisePlanOptional = calendarExercisePlanRepository.findById(calendarExercisePlan.getId());
        if (calendarExercisePlanOptional.isEmpty()){
            throw new ResourceNotFoundException("CalendarExercisePlan", "Id", calendarExercisePlan.getId());
        }else {
            CalendarExercisePlan temp = calendarExercisePlanOptional.get();
            calendarExercisePlanOptional.get().setAerobic(calendarExercisePlan.getAerobic());
            calendarExercisePlanOptional.get().setMuscle(calendarExercisePlan.getMuscle());
            calendarExercisePlanOptional.get().setStretch(calendarExercisePlan.getStretch());
            calendarExercisePlanOptional.get().setYoga(calendarExercisePlan.getYoga());
            calendarExercisePlanOptional.get().setDetail(calendarExercisePlan.getDetail());
            calendarExercisePlanRepository.save(temp);
            return temp;
        }
    }

    public CalendarExercisePlan findCalendarExercisePlanByCalendar(Calendar calendar){
        return calendarExercisePlanRepository.findByCalendar(calendar);
    }
}
