package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.comasy.entity.Delivery;
import pds.comasy.repository.DeliveryRepository;

import java.util.List;

@Service
public class ReceptionService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
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
}
