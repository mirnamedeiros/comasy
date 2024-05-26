package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.comasy.entity.NotificationVisitor;
import pds.comasy.repository.NotificationVisitorRepository;

import java.util.List;

@Service
public class ReceptionService {

    @Autowired
    private NotificationVisitorRepository notificationVisitorRepository;

    public NotificationVisitor createNotification(NotificationVisitor notificationVisitor) {
        return notificationVisitorRepository.save(notificationVisitor);
    }

    public List<NotificationVisitor> getPendingNotifications() {
        return notificationVisitorRepository.findByAuthorizedFalse();
    }

    public NotificationVisitor updateNotification(Integer id, NotificationVisitor newNotificationVisitor) {
        return notificationVisitorRepository.findById(id).map(notificationVisitor -> {
            notificationVisitor.setVisitor(newNotificationVisitor.getVisitor());
            notificationVisitor.setTime(newNotificationVisitor.getTime());
            notificationVisitor.setAuthorized(newNotificationVisitor.getAuthorized());
            return notificationVisitorRepository.save(newNotificationVisitor);
        }).orElse(null);
    }

    public void deleteNotification(Integer id) {
        notificationVisitorRepository.deleteById(id);
    }

    public NotificationVisitor authorizeNotification(Integer number) {
        NotificationVisitor notification = notificationVisitorRepository.findByNumber(number);
        if (notification != null) {
            notification.setAuthorized(true);
            return notificationVisitorRepository.save(notification);
        } else {
            return null;
        }
    }
}
