package PTR.PTR.controller;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarDietPlan;
import PTR.PTR.model.CalendarExerciseRecord;
import PTR.PTR.service.CalendarExerciseRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CalendarExerciseRecordController {
    CalendarExerciseRecordService calendarExerciseRecordService;

    public CalendarExerciseRecordController(CalendarExerciseRecordService calendarExerciseRecordService) {
        this.calendarExerciseRecordService = calendarExerciseRecordService;
    }

    @PostMapping("/api/calendarExerciseRecord")
    public CalendarExerciseRecord saveCalendarExerciseRecord(@RequestBody CalendarExerciseRecord calendarExerciseRecord){
        return calendarExerciseRecordService.saveCalendarExerciseRecord(calendarExerciseRecord);
    }

    @GetMapping("/api/calendarExerciseRecord")
    public Optional<CalendarExerciseRecord> getCalendarExerciseRecord(@RequestBody CalendarExerciseRecord calendarExerciseRecord){
        return calendarExerciseRecordService.getCalendarExerciseRecord(calendarExerciseRecord);
    }

    @DeleteMapping("/api/calendarExerciseRecord")
    public void deleteCalendarExerciseRecord(@RequestBody CalendarExerciseRecord calendarExerciseRecord){
        calendarExerciseRecordService.deleteCalendarExerciseRecord(calendarExerciseRecord);
    }

    @PutMapping("/api/calendarExerciseRecord")
    public CalendarExerciseRecord updateCalendarExerciseRecord(@RequestBody CalendarExerciseRecord calendarExerciseRecord){
        return calendarExerciseRecordService.updateCalendarExerciseRecord(calendarExerciseRecord);
    }

    @PostMapping("/api/findCalendarExerciseRecordByCalendar")
    public ResponseEntity<CalendarExerciseRecord> findCalendarExerciseRecordByCalendar(@RequestBody Calendar calendar){
        return new ResponseEntity<>(calendarExerciseRecordService.findCalendarExerciseRecordByCalendar(calendar), HttpStatus.OK);
    }
}
