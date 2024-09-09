package PTR.PTR.service;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureScrap;
import PTR.PTR.model.User;
import PTR.PTR.repository.LectureScrapRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LectureScrapService {
    LectureScrapRepository lectureScrapRepository;

    public LectureScrapService(LectureScrapRepository lectureScrapRepository) {
        this.lectureScrapRepository = lectureScrapRepository;
    }

    public String scrapLecture(LectureScrap lectureScrap){
        if (null==lectureScrapRepository.findByUserAndLecture(lectureScrap.getUser(), lectureScrap.getLecture())){
            lectureScrap.setCreatedAt(LocalDateTime.now());
            lectureScrapRepository.save(lectureScrap);
            return "스크랩 저장 완료";
        }
        return "이미 스크랩 되었습니다.";
    }

    public String deleteScrapLecture(LectureScrap lectureScrap){
        lectureScrapRepository.delete(lectureScrap);
        return "삭제";
    }
    
    public List<LectureScrap> myScrapLecture(User user){
        return lectureScrapRepository.findByUser(user);
    }

    public LectureScrap findScrapLectureByUserAndLecture(LectureScrap lectureScrap){
        return lectureScrapRepository.findByUserAndLecture(lectureScrap.getUser(), lectureScrap.getLecture());
    }
}
