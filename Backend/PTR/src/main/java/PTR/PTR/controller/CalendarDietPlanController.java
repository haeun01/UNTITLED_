package PTR.PTR.controller;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarDietPlan;
import PTR.PTR.service.CalendarDietPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CalendarDietPlanController {
    CalendarDietPlanService calendarDietPlanService;

    public CalendarDietPlanController(CalendarDietPlanService calendarDietPlanService) {
        this.calendarDietPlanService = calendarDietPlanService;
    }
    @PostMapping("/api/calendarDietPlan")
    public CalendarDietPlan saveCalendarDietPlan(@RequestBody CalendarDietPlan calendarDietPlan){
        return calendarDietPlanService.saveCalendarDietPlan(calendarDietPlan);
    }

    @GetMapping("/api/calendarDietPlan")
    public Optional<CalendarDietPlan> getCalendarDietPlan(@RequestBody CalendarDietPlan calendarDietPlan){
        return calendarDietPlanService.getCalendarDietPlan(calendarDietPlan);
    }

    @DeleteMapping("/api/calendarDietPlan")
    public void deleteCalendarDietPlan(@RequestBody CalendarDietPlan calendarDietPlan){
        calendarDietPlanService.deleteCalendarDietPlan(calendarDietPlan);
    }

    @PutMapping("/api/calendarDietPlan")
    public CalendarDietPlan updateCalendarDietPlan(@RequestBody CalendarDietPlan calendarDietPlan){
        return calendarDietPlanService.updateCalendarDietPlan(calendarDietPlan);
    }

    @PostMapping("/api/findCalendarDietPlanByCalendar")
    public ResponseEntity<CalendarDietPlan> findCalendarDietPlanByCalendar(@RequestBody Calendar calendar){
        return new ResponseEntity<>(calendarDietPlanService.findCalendarDietPlanByCalendar(calendar), HttpStatus.OK);
    }

    
}
