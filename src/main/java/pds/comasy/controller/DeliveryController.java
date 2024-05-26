package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pds.comasy.dto.DeliveryDto;
import pds.comasy.entity.Delivery;
import pds.comasy.exceptions.EntityAlreadyExistsException;
import pds.comasy.exceptions.EntitySaveFailureException;
import pds.comasy.exceptions.NotFoundException;
import pds.comasy.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryDto> createDelivery(@RequestBody DeliveryDto delivery) {
        try {
            DeliveryDto newDelivery = deliveryService.createDelivery(delivery);
            return new ResponseEntity<>(newDelivery, HttpStatus.CREATED);
        } catch (EntityAlreadyExistsException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (EntitySaveFailureException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDto>> getAllDeliveries() {
        List<DeliveryDto> deliveries = deliveryService.getAllDeliveries();
        if (deliveries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<DeliveryDto>> getPendingDeliveries() {
        List<DeliveryDto> pendingDeliveries = deliveryService.getPendingDeliveries();
        if (pendingDeliveries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pendingDeliveries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable("id") Integer id) {
        try {
            DeliveryDto delivery = deliveryService.getDeliveryById(id);
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryDto> updateDelivery(@PathVariable("id") Integer id, @RequestBody DeliveryDto delivery) {
        try {
            DeliveryDto updatedDelivery = deliveryService.updateDelivery(id, delivery);
            return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (EntitySaveFailureException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDelivery(@PathVariable("id") Integer id) {
        try {
            deliveryService.deleteDelivery(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

