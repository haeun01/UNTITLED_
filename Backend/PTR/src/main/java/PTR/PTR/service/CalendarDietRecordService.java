package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarDietRecord;
import PTR.PTR.repository.CalendarDietRecordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CalendarDietRecordService {
    CalendarDietRecordRepository calendarDietRecordRepository;

    public CalendarDietRecordService(CalendarDietRecordRepository calendarDietRecordRepository) {
        this.calendarDietRecordRepository = calendarDietRecordRepository;
    }
    //작성
    public CalendarDietRecord saveCalendarDietRecord(CalendarDietRecord calendarDietRecord){
        return calendarDietRecordRepository.save(calendarDietRecord);
    }

    //읽기
    public Optional<CalendarDietRecord> getCalendarDietRecord(CalendarDietRecord calendarDietRecord){
        return calendarDietRecordRepository.findById(calendarDietRecord.getId());
    }

    //삭제
    public void deleteCalendarDietRecord(CalendarDietRecord calendarDietRecord){
        calendarDietRecordRepository.deleteById(calendarDietRecord.getId());
    }

    //수정
    public CalendarDietRecord updateCalendarDietRecord(CalendarDietRecord calendarDietRecord){
        Optional<CalendarDietRecord> calendarDietRecordOptional = calendarDietRecordRepository.findById(calendarDietRecord.getId());
        if (calendarDietRecordOptional.isEmpty()){
            throw new ResourceNotFoundException("CalendarDietRecord", "Id", calendarDietRecord.getId());
        }else {
            CalendarDietRecord temp = calendarDietRecordOptional.get();
            calendarDietRecordOptional.get().setCarbohydrate(calendarDietRecord.getCarbohydrate());
            calendarDietRecordOptional.get().setProtein(calendarDietRecord.getProtein());
            calendarDietRecordOptional.get().setFat(calendarDietRecord.getFat());
            calendarDietRecordOptional.get().setDetail(calendarDietRecord.getDetail());
            calendarDietRecordRepository.save(temp);
            return temp;
        }
    }

    public CalendarDietRecord findCalendarDietRecordByCalendar(Calendar calendar){
        return calendarDietRecordRepository.findByCalendar(calendar);
    }
}
