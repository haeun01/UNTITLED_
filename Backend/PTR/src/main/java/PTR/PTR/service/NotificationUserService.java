package PTR.PTR.service;

import PTR.PTR.model.*;
import PTR.PTR.repository.NotificationUserRepository;
import PTR.PTR.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationUserService {
    NotificationUserRepository notificationUserRepository;
    SubscriptionRepository subscriptionRepository;

    public NotificationUserService(NotificationUserRepository notificationUserRepository, SubscriptionRepository subscriptionRepository) {
        this.notificationUserRepository = notificationUserRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void lectureNotificationUser(Notification notification, Teacher teacher){
        List<User> users = new ArrayList<>(
                subscriptionRepository.findByTeacher(teacher).stream().map(Subscription::getUser).toList());
        for (int i=0; i<users.size(); i++){
            NotificationUser notificationUser = new NotificationUser();
            notificationUser.setNotification(notification);
            notificationUser.setUser(users.get(i));
            notificationUser.setStatus(Status.UNCONFIRMED);
            notificationUser.setIdentifyAt(LocalDateTime.now());
            notificationUserRepository.save(notificationUser);
        }
    }

    public void inquiryReplyNotification(Notification notification, InquiryReply inquiryReply){
        NotificationUser notificationUser = new NotificationUser();
        notificationUser.setNotification(notification);
        notificationUser.setUser(inquiryReply.getInquiry().getUser());
        notificationUser.setStatus(Status.UNCONFIRMED);
        notificationUser.setIdentifyAt(LocalDateTime.now());
        notificationUserRepository.save(notificationUser);
    }

    public List<NotificationUser> myNotification(User user){
        return notificationUserRepository.findByUser(user).reversed();
    }

    public String deleteNotification(NotificationUser notificationUser){
        notificationUserRepository.delete(notificationUser);
        return "삭제";
    }

    public NotificationUser confirmNotification(NotificationUser notificationUser){
        notificationUser.setStatus(Status.CONFIRM);
        return notificationUserRepository.save(notificationUser);
    }

    public NotificationUser unconfirmedNotification(NotificationUser notificationUser){
        notificationUser.setStatus(Status.UNCONFIRMED);
        return notificationUserRepository.save(notificationUser);
    }


}
