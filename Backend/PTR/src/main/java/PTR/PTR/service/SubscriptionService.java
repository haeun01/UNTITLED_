package PTR.PTR.service;

import PTR.PTR.model.*;
import PTR.PTR.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {
    SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public String subscription(Subscription subscription){
        Subscription exist = subscriptionRepository.findByUserAndTeacher(subscription.getUser(), subscription.getTeacher());
        if (null==exist){
            subscription.setCreatedAt(LocalDateTime.now());
            subscriptionRepository.save(subscription);
            return "정상 처리됨";
        }
        return "이미 구독하였습니다.";
    }

    public String subscriptionCansel(Subscription subscription){
        subscriptionRepository.delete(subscription);
        return "구독 취소";
    }

    public List<Subscription> mySubscription(User user){
        return subscriptionRepository.findByUser(user);
    }

    public int teacherSubscription(Teacher teacher){
        return subscriptionRepository.findByTeacher(teacher).size();
    }

    public Subscription subscriptionCheck(Subscription subscription){
        return subscriptionRepository.findByUserAndTeacher(subscription.getUser(), subscription.getTeacher());
    }
}
