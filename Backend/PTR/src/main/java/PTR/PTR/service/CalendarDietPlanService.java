package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarDietPlan;
import PTR.PTR.repository.CalendarDietPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarDietPlanService {
    CalendarDietPlanRepository calendarDietPlanRepository;

    public CalendarDietPlanService(CalendarDietPlanRepository calendarDietPlanRepository) {
        this.calendarDietPlanRepository = calendarDietPlanRepository;
    }

    //작성
    public CalendarDietPlan saveCalendarDietPlan(CalendarDietPlan calendarDietPlan){
        return calendarDietPlanRepository.save(calendarDietPlan);
    }

    //읽기
    public Optional<CalendarDietPlan> getCalendarDietPlan(CalendarDietPlan calendarDietPlan){
        return calendarDietPlanRepository.findById(calendarDietPlan.getId());
    }

    //삭제
    public void deleteCalendarDietPlan(CalendarDietPlan calendarDietPlan){
        calendarDietPlanRepository.deleteById(calendarDietPlan.getId());
    }

    //수정
    public CalendarDietPlan updateCalendarDietPlan(CalendarDietPlan calendarDietPlan){
        Optional<CalendarDietPlan> calendarDietPlanOptional = calendarDietPlanRepository.findById(calendarDietPlan.getId());
        if (calendarDietPlanOptional.isEmpty()){
            throw new ResourceNotFoundException("CalendarDietPlan", "Id", calendarDietPlan.getId());
        }else {
            CalendarDietPlan temp = calendarDietPlanOptional.get();
            calendarDietPlanOptional.get().setCarbohydrate(calendarDietPlan.getCarbohydrate());
            calendarDietPlanOptional.get().setProtein(calendarDietPlan.getProtein());
            calendarDietPlanOptional.get().setFat(calendarDietPlan.getFat());
            calendarDietPlanOptional.get().setDetail(calendarDietPlan.getDetail());
            calendarDietPlanRepository.save(temp);
            return temp;
        }
    }

    public CalendarDietPlan findCalendarDietPlanByCalendar(Calendar calendar){
        return calendarDietPlanRepository.findByCalendar(calendar);
    }
}
