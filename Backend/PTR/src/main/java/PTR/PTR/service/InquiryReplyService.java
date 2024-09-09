package PTR.PTR.service;

import PTR.PTR.model.Inquiry;
import PTR.PTR.model.InquiryReply;
import PTR.PTR.repository.InquiryReplyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InquiryReplyService {
    InquiryReplyRepository inquiryReplyRepository;
    NotificationService notificationService;

    public InquiryReplyService(InquiryReplyRepository inquiryReplyRepository, NotificationService notificationService) {
        this.inquiryReplyRepository = inquiryReplyRepository;
        this.notificationService = notificationService;
    }

    public InquiryReply replyInquiry(InquiryReply inquiryReply){
        inquiryReply.setCreatedAt(LocalDateTime.now());
        inquiryReplyRepository.save(inquiryReply);
        notificationService.inquiryReplyNotification(inquiryReply);
        return inquiryReply;
    }

    public List<InquiryReply> findInquiryReply(Inquiry inquiry){
        return inquiryReplyRepository.findByInquiry(inquiry);
    }
}
