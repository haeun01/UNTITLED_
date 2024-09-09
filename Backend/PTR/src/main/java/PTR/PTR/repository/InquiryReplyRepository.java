package PTR.PTR.repository;

import PTR.PTR.model.Inquiry;
import PTR.PTR.model.InquiryReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryReplyRepository extends JpaRepository<InquiryReply,Long> {
    List<InquiryReply> findByInquiry(Inquiry inquiry);
}
