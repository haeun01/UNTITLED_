package PTR.PTR.service;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureUser;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import PTR.PTR.repository.LectureUserRepository;
import PTR.PTR.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureUserService {
    LectureUserRepository lectureUserRepository;
    UserRepository userRepository;
    LectureService lectureService;

    public LectureUserService(LectureUserRepository lectureUserRepository, UserRepository userRepository, LectureService lectureService) {
        this.lectureUserRepository = lectureUserRepository;
        this.userRepository = userRepository;
        this.lectureService = lectureService;
    }

    public String buyLecture(LectureUser lectureUser){
        lectureUser.setUser(userRepository.findById(lectureUser.getUser().getUserId()).get());
        lectureUser.setCreatedAt(LocalDateTime.now());
        lectureUser.setTeacherRating(-1);
        int price = lectureUser.getLecture().getPrice();
        int coin = lectureUser.getUser().getCoin();
        if (coin >= price){
            if (null==lectureUserRepository.findByUserAndLecture(lectureUser.getUser(),lectureUser.getLecture())){
                coin = coin - price;
                lectureUser.getUser().setCoin(coin);
                userRepository.save(lectureUser.getUser());
                lectureUserRepository.save(lectureUser);
                return "결제 완료";
            }else {
                return "이미 결제함";
            }
        }else {
            return "잔액 부족";
        }
    }
    public List<LectureUser> myBuyLecture(User user){
        return lectureUserRepository.findByUser(user);
    }

    public int buyNumber(Lecture lecture){
        return lectureUserRepository.findByLecture(lecture).size();
    }

    public LectureUser changeLectureUser(LectureUser lectureUser){
        LectureUser changeLectureUser = lectureUserRepository.findByUserAndLecture(lectureUser.getUser(), lectureUser.getLecture());
        changeLectureUser.setTeacherReview(lectureUser.getTeacherReview());
        changeLectureUser.setTeacherRating(lectureUser.getTeacherRating());
        return lectureUserRepository.save(changeLectureUser);
    }

    public float ratingAVG(Lecture lecture){
        List<Integer> ratings = lectureUserRepository.findByLecture(lecture).stream().map(LectureUser::getTeacherRating)
                .filter(teacherRating -> teacherRating >=0).toList();
        float ratingAvg = 0;
        for (int i=0; i<ratings.size(); i++){
            ratingAvg = ratingAvg + ratings.get(i);
        }
        return ratingAvg / ratings.size();
    }

    public List<LectureUser> findReviewByLecture(Lecture lecture){
        return lectureUserRepository.findByLecture(lecture).stream()
                .filter(l->l.getTeacherReview()!=null).collect(Collectors.toList());
    }

    public List<LectureUser> findByLectureId(Lecture lecture){
        return lectureUserRepository.findByLecture(lecture).stream().toList();
    }

    public List<LectureUser> todayLectureUser(){
        LocalDate localDate = LocalDate.now();
        return lectureUserRepository.findAll().stream().filter(l->l.getCreatedAt().toLocalDate().equals(localDate)).collect(Collectors.toList());
    }

    public List<LectureUser> AllLectureUser(){
        return lectureUserRepository.findAll();
    }

    public List<LectureUser> findLectureUserByTeacher(Teacher teacher){
        List<Lecture> lectures = lectureService.findTeacherLecture(teacher);
        return lectureUserRepository.findAllByLectureIn(lectures);
    }
}
