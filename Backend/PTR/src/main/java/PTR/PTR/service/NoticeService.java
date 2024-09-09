package PTR.PTR.service;

import PTR.PTR.model.Notice;
import PTR.PTR.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeService {
    NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public Notice createNotice(Notice notice){
        notice.setCreatedAt(LocalDateTime.now());
        return noticeRepository.save(notice);
    }

    public List<Notice> findNotice(){
        return noticeRepository.findAll().reversed();
    }

    public List<Notice> searchNotice(String title){
        return noticeRepository.findAll().stream().filter(n->n.getTitle().contains(title)).collect(Collectors.toList()).reversed();
    }
}
