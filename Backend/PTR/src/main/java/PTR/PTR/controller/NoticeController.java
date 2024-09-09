package PTR.PTR.controller;

import PTR.PTR.model.Notice;
import PTR.PTR.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {
    NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    // 공지사항 생성
    @PostMapping("/api/createNotice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Notice> createNotice(@RequestBody Notice notice){
        return new ResponseEntity<>(noticeService.createNotice(notice), HttpStatus.OK);
    }
    // 전체 공지사항 조회
    @PostMapping("/api/findNotice")
    public ResponseEntity<List<Notice>> findNotice(){
        return new ResponseEntity<>(noticeService.findNotice(), HttpStatus.OK);
    }
    // 공지사항 검색
    @PostMapping("/api/searchNotice")
    public ResponseEntity<List<Notice>> searchNotice(@RequestBody String title){
        return new ResponseEntity<>(noticeService.searchNotice(title), HttpStatus.OK);
    }
}
