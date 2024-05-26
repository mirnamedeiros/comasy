package pds.comasy.mapper;

import pds.comasy.dto.DeliveryDto;
import pds.comasy.entity.Delivery;

import java.util.ArrayList;
import java.util.List;

public class DeliveryMapper {

    public static Delivery mapToDelivery(DeliveryDto deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setNumber(delivery.getNumber());
        delivery.setResidentId(deliveryDTO.getResidentId());
        delivery.setArrivalDate(deliveryDTO.getArrivalDate());
        delivery.setDelivered(deliveryDTO.getDelivered());

        return delivery;
    }

    public static DeliveryDto mapToDeliveryDTO(Delivery delivery) {
        DeliveryDto deliveryDTO = new DeliveryDto();
        deliveryDTO.setNumber(delivery.getNumber());
        deliveryDTO.setResidentId(delivery.getResidentId());
        deliveryDTO.setArrivalDate(delivery.getArrivalDate());
        deliveryDTO.setDelivered(delivery.getDelivered());

        return deliveryDTO;
    }

    public static List<DeliveryDto> mapToListDeliveryDTO(List<Delivery> deliveryList) {
        List<DeliveryDto> deliveryDTOList = new ArrayList<>();
        for (Delivery delivery : deliveryList) {
            deliveryDTOList.add(mapToDeliveryDTO(delivery));
        }
        return deliveryDTOList;
    }
}
