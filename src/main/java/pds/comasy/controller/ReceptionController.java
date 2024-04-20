package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pds.comasy.entity.Delivery;
import pds.comasy.service.ReceptionService;

import java.util.List;

@RestController
@RequestMapping("/api/reception")
public class ReceptionController {

    @Autowired
    private ReceptionService receptionService;

    @PostMapping("/delivery")
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        try {
            Delivery newDelivery = receptionService.createDelivery(delivery);
            return new ResponseEntity<>(newDelivery, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delivery")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> deliveries = receptionService.getAllDeliveries();
        if (deliveries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable("id") Integer id) {
        Delivery delivery = receptionService.getDeliveryById(id);
        if (delivery != null) {
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/delivery/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable("id") Integer id, @RequestBody Delivery delivery) {
        Delivery updatedDelivery = receptionService.updateDelivery(id, delivery);
        if (updatedDelivery != null) {
            return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delivery/{id}")
    public ResponseEntity<HttpStatus> deleteDelivery(@PathVariable("id") Integer id) {
        try {
            receptionService.deleteDelivery(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
