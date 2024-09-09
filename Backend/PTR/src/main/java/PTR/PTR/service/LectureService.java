package PTR.PTR.service;

import PTR.PTR.model.*;
import PTR.PTR.repository.LectureCategoryRepository;
import PTR.PTR.repository.LectureRepository;
import PTR.PTR.repository.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LectureService {
    LectureRepository lectureRepository;
    NotificationService notificationService;
    LectureCategoryRepository lectureCategoryRepository;



    public LectureService(LectureRepository lectureRepository, NotificationService notificationService, LectureCategoryRepository lectureCategoryRepository) {
        this.lectureRepository = lectureRepository;
        this.notificationService = notificationService;
        this.lectureCategoryRepository = lectureCategoryRepository;
    }

    public Lecture createLecture(Lecture lecture){
        lecture.setCreatedAt(LocalDateTime.now());
        notificationService.lectureNotification(lecture.getTeacher());
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findTeacherLecture(Teacher teacher){
        return lectureRepository.findByTeacher(teacher);
    }

    public List<Lecture> findTeacherLectureReversed(Teacher teacher){
        return lectureRepository.findByTeacher(teacher).reversed();
    }

    public List<Lecture> searchLecture(String search){
        return lectureRepository.findByLectureNameContainingOrDescriptionContaining(search, search);
    }

    public List<Lecture> findPriceLecture(int minPrice, int maxPrice){
        return lectureRepository.findAll().stream()
                .filter(l->l.getPrice()>=minPrice && l.getPrice()<=maxPrice).collect(Collectors.toList());
    }

    public String saveLectureCategory(List<LectureCategory> lectureCategories){
        if (!lectureCategoryRepository.findByLecture(lectureCategories.getFirst().getLecture()).isEmpty()){
            lectureCategoryRepository.deleteAll(lectureCategoryRepository.findByLecture(lectureCategories.getFirst().getLecture()));
        }
        lectureCategoryRepository.saveAll(lectureCategories);
        return "카테고리가 저장됨";
    }
    public String deleteLecturerCategory(Lecture lecture){
        if (!lectureCategoryRepository.findByLecture(lecture).isEmpty()){
            lectureCategoryRepository.deleteAll(lectureCategoryRepository.findByLecture(lecture));
        }
        return "카테고리가 삭제됨";
    }
    public List<LectureCategory> findLectureCategory(Lecture lecture){
        return lectureCategoryRepository.findByLecture(lecture);
    }
    public List<Lecture> findLectureByCategory(Category category){
        return lectureCategoryRepository.findByCategory(category)
                .stream().map(LectureCategory::getLecture).collect(Collectors.toList());
    }

    public List<Lecture> findLectureAllByCategoryIn(List<Category> categories){
        return lectureCategoryRepository.findAllByCategoryIn(categories)
                .stream().map(LectureCategory::getLecture).collect(Collectors.toList())
                .reversed().stream().limit(6).collect(Collectors.toList());
    }

    public List<Lecture> findAllLecture(){
        return lectureRepository.findAll();
    }

    public Lecture getLectureById(long id) {
        return lectureRepository.findById(id).get();
    }

    public List<Lecture> todayLecture(){
        LocalDate localDate = LocalDate.now();
        return lectureRepository.findAll().stream().filter(l->l.getCreatedAt().toLocalDate().equals(localDate)).collect(Collectors.toList());
    }

    public List<Lecture> searchLectureByTeacherName(String teacherName) {
        return lectureRepository.findAll().stream()
                .filter(l -> l.getTeacher() != null && l.getTeacher().getUser().getUserName().contains(teacherName))
                .collect(Collectors.toList());
    }
}
