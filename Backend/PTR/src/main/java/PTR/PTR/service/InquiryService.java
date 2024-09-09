package PTR.PTR.service;

import PTR.PTR.model.Inquiry;
import PTR.PTR.model.InquiryReply;
import PTR.PTR.model.User;
import PTR.PTR.repository.InquiryReplyRepository;
import PTR.PTR.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryService {
    InquiryRepository inquiryRepository;
    InquiryReplyService inquiryReplyService;
    InquiryReplyRepository inquiryReplyRepository;

    public InquiryService(InquiryRepository inquiryRepository, InquiryReplyService inquiryReplyService,
                          InquiryReplyRepository inquiryReplyRepository) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryReplyService = inquiryReplyService;
        this.inquiryReplyRepository = inquiryReplyRepository;
    }

    public Inquiry askInquiry(Inquiry inquiry){
        inquiry.setCreatedAt(LocalDateTime.now());
        return inquiryRepository.save(inquiry);
    }

    public List<Inquiry> findInquiry(){
        List<Inquiry> inquiryList = inquiryRepository.findAll();
        List<Inquiry> newInquiryList = new ArrayList<>();
        newInquiryList = inquiryList.stream().map((data)->{
            List<InquiryReply> inquiryReplyList = inquiryReplyRepository.findByInquiry(data);
            if (inquiryReplyList.size() > 0) {
                data.setTitle(data.getTitle() + " [답변완료]");
                data.setText(data.getText() + "\n\n[답변]\n"
                        + inquiryReplyList.getFirst().getText());
            }
            return data;
        }).collect(Collectors.toList());
        return newInquiryList;
    }

    public List<Inquiry> myInquiry(User user){
        return inquiryRepository.findByUser(user);
    }

    public List<Inquiry> findNotReply(){
        return inquiryRepository.findAll().stream().filter(l-> inquiryReplyService.findInquiryReply(l).isEmpty()).collect(Collectors.toList());
    }
}
