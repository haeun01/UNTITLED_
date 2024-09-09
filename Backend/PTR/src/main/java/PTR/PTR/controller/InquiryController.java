package PTR.PTR.controller;

import PTR.PTR.model.Inquiry;
import PTR.PTR.model.InquiryReply;
import PTR.PTR.model.User;
import PTR.PTR.service.InquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InquiryController {
    InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }
    // 1:1 문의하기
    @PostMapping("/api/askInquiry")
    public ResponseEntity<Inquiry> askInquiry(@RequestBody Inquiry inquiry){
        return new ResponseEntity<>(inquiryService.askInquiry(inquiry), HttpStatus.OK);
    }
    // 모든 1:1 문의 조회
    @GetMapping("/api/findInquiry")
    public ResponseEntity<List<Inquiry>> findInquiry(){
        return new ResponseEntity<>(inquiryService.findInquiry(), HttpStatus.OK);
    }
    // 유저의 1:1문의찾기
    @PostMapping("/api/myInquiry")
    public ResponseEntity<List<Inquiry>> myInquiry(@RequestBody User user){
        return new ResponseEntity<>(inquiryService.myInquiry(user), HttpStatus.OK);
    }
    @GetMapping("/api/findNotReply")
    public ResponseEntity<List<Inquiry>> findNotReply(){
        return new ResponseEntity<>(inquiryService.findNotReply(), HttpStatus.OK);
    }
}
