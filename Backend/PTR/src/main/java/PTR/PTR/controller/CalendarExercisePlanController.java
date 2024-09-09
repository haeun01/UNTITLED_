package PTR.PTR.controller;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarDietPlan;
import PTR.PTR.model.CalendarExercisePlan;
import PTR.PTR.service.CalendarExercisePlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CalendarExercisePlanController {
    CalendarExercisePlanService calendarExercisePlanService;

    public CalendarExercisePlanController(CalendarExercisePlanService calendarExercisePlanService) {
        this.calendarExercisePlanService = calendarExercisePlanService;
    }

    @PostMapping("/api/calendarExercisePlan")
    public CalendarExercisePlan saveCalendarExercisePlan(@RequestBody CalendarExercisePlan calendarExercisePlan){
        return calendarExercisePlanService.saveCalendarExercisePlan(calendarExercisePlan);
    }

    @GetMapping("/api/calendarExercisePlan")
    public Optional<CalendarExercisePlan> getCalendarExercisePlan(@RequestBody CalendarExercisePlan calendarExercisePlan){
        return calendarExercisePlanService.getCalendarExercisePlan(calendarExercisePlan);
    }

    @DeleteMapping("/api/calendarExercisePlan")
    public void deleteCalendarExercisePlan(@RequestBody CalendarExercisePlan calendarExercisePlan){
        calendarExercisePlanService.deleteCalendarExercisePlan(calendarExercisePlan);
    }

    @PutMapping("/api/calendarExercisePlan")
    public CalendarExercisePlan updateCalendarExercisePlan(@RequestBody CalendarExercisePlan calendarExercisePlan){
        return calendarExercisePlanService.updateCalendarExercisePlan(calendarExercisePlan);
    }

    @PostMapping("/api/findCalendarExercisePlanByCalendar")
    public ResponseEntity<CalendarExercisePlan> findCalendarExercisePlanByCalendar(@RequestBody Calendar calendar){
        return new ResponseEntity<>(calendarExercisePlanService.findCalendarExercisePlanByCalendar(calendar), HttpStatus.OK);
    }
}
