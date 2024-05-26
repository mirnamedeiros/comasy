package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.comasy.dto.DeliveryDto;
import pds.comasy.entity.Delivery;
import pds.comasy.exceptions.EntityAlreadyExistsException;
import pds.comasy.exceptions.EntitySaveFailureException;
import pds.comasy.exceptions.NotFoundException;
import pds.comasy.mapper.DeliveryMapper;
import pds.comasy.repository.DeliveryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public DeliveryDto createDelivery(DeliveryDto delivery) throws EntityAlreadyExistsException, EntitySaveFailureException {
        try {
            Delivery savedDelivery = deliveryRepository.save(DeliveryMapper.mapToDelivery(delivery));
            return DeliveryMapper.mapToDeliveryDTO(savedDelivery);
        } catch (Exception e) {
            throw new EntitySaveFailureException("Failed to save delivery");
        }
    }

    public List<DeliveryDto> getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return DeliveryMapper.mapToListDeliveryDTO(deliveries);
    }

    public List<DeliveryDto> getPendingDeliveries() {
        List<DeliveryDto> pendingDeliveries = DeliveryMapper.mapToListDeliveryDTO(deliveryRepository.findByDeliveredFalse());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        pendingDeliveries.forEach(delivery -> {
            LocalDate arrivalDate = delivery.getArrivalDate().toLocalDate();
            String formattedDate = arrivalDate.format(formatter);
            delivery.setFormattedArrivalDate(formattedDate);
        });
        return pendingDeliveries;
    }

    public DeliveryDto getDeliveryById(Integer id) throws NotFoundException {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Delivery not found"));
        return DeliveryMapper.mapToDeliveryDTO(delivery);
    }

    public DeliveryDto updateDelivery(Integer id, DeliveryDto newDelivery) throws NotFoundException, EntitySaveFailureException {
        if (deliveryRepository.existsById(id)) {
            Delivery savedDelivery = deliveryRepository.findById(id).map(delivery -> {
                delivery.setResidentId(newDelivery.getResidentId());
                delivery.setArrivalDate(newDelivery.getArrivalDate());
                delivery.setDelivered(newDelivery.getDelivered());
                return deliveryRepository.save(delivery);
            }).orElseThrow(() -> new EntitySaveFailureException("Failed to save delivery"));
            return DeliveryMapper.mapToDeliveryDTO(savedDelivery);
        } else {
            throw new NotFoundException("Delivery not found");
        }
    }

    public void deleteDelivery(Integer id) throws NotFoundException {
        if (deliveryRepository.existsById(id)) {
            deliveryRepository.deleteById(id);
        } else {
            throw new NotFoundException("Delivery not found");
        }
    }
}