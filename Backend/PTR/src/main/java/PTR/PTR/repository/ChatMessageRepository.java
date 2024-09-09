package PTR.PTR.repository;

import PTR.PTR.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // 특정 강의에 대한 채팅 메시지를 조회하는 메서드
    List<ChatMessage> findByLectureId(String lectureId);
}
