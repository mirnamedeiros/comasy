package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.comasy.entity.Delivery;
import pds.comasy.entity.NotificationVisitor;
import pds.comasy.repository.DeliveryRepository;
import pds.comasy.repository.NotificationVisitorRepository;

import java.util.List;

@Service
public class ReceptionService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private NotificationVisitorRepository notificationVisitorRepository;

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public List<Delivery> getPendingDeliveries() {
        return deliveryRepository.findByDeliveredFalse();
    }

    public Delivery getDeliveryById(Integer id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    public Delivery updateDelivery(Integer id, Delivery newDelivery) {
        return deliveryRepository.findById(id).map(delivery -> {
            delivery.setResidentId(newDelivery.getResidentId());
            delivery.setArrivalDate(newDelivery.getArrivalDate());
            delivery.setDelivered(newDelivery.getDelivered());
            return deliveryRepository.save(delivery);
        }).orElse(null);
    }

    public void deleteDelivery(Integer id) {
        deliveryRepository.deleteById(id);
    }

    public NotificationVisitor createNotification(NotificationVisitor notificationVisitor) {
        return notificationVisitorRepository.save(notificationVisitor);
    }

    public List<NotificationVisitor> getAllNotifications() {
        return notificationVisitorRepository.findAll();
    }

    public NotificationVisitor getNotificationById(Integer id) {
        return notificationVisitorRepository.findById(id).orElse(null);
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
