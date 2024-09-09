package PTR.PTR.service;

import PTR.PTR.exception.ResourceNotFoundException;
import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarExerciseRecord;
import PTR.PTR.repository.CalendarExerciseRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarExerciseRecordService {
    CalendarExerciseRecordRepository calendarExerciseRecordRepository;

    public CalendarExerciseRecordService(CalendarExerciseRecordRepository calendarExerciseRecordRepository) {
        this.calendarExerciseRecordRepository = calendarExerciseRecordRepository;
    }

    //작성
    public CalendarExerciseRecord saveCalendarExerciseRecord(CalendarExerciseRecord calendarExerciseRecord){
        return calendarExerciseRecordRepository.save(calendarExerciseRecord);
    }

    //읽기
    public Optional<CalendarExerciseRecord> getCalendarExerciseRecord(CalendarExerciseRecord calendarExerciseRecord){
        return calendarExerciseRecordRepository.findById(calendarExerciseRecord.getId());
    }

    //삭제
    public void deleteCalendarExerciseRecord(CalendarExerciseRecord calendarExerciseRecord){
        calendarExerciseRecordRepository.deleteById(calendarExerciseRecord.getId());
    }

    //수정
    public CalendarExerciseRecord updateCalendarExerciseRecord(CalendarExerciseRecord calendarExerciseRecord){
        Optional<CalendarExerciseRecord> calendarExerciseRecordOptional = calendarExerciseRecordRepository.findById(calendarExerciseRecord.getId());
        if (calendarExerciseRecordOptional.isEmpty()){
            throw new ResourceNotFoundException("CalendarExerciseRecord","Id", calendarExerciseRecord.getId());
        }else {
            CalendarExerciseRecord temp = calendarExerciseRecordOptional.get();
            calendarExerciseRecordOptional.get().setAerobic(calendarExerciseRecord.getAerobic());
            calendarExerciseRecordOptional.get().setMuscle(calendarExerciseRecord.getMuscle());
            calendarExerciseRecordOptional.get().setStretch(calendarExerciseRecord.getStretch());
            calendarExerciseRecordOptional.get().setYoga(calendarExerciseRecord.getYoga());
            calendarExerciseRecordOptional.get().setDetail(calendarExerciseRecord.getDetail());
            calendarExerciseRecordRepository.save(temp);
            return temp;
        }
    }

    public CalendarExerciseRecord findCalendarExerciseRecordByCalendar(Calendar calendar){
        return calendarExerciseRecordRepository.findByCalendar(calendar);
    }
}
