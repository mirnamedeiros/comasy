package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.ResidentDto;
import pds.comasy.entity.NotificationVisitor;
import pds.comasy.entity.Visitor;
import pds.comasy.service.ReceptionService;
import pds.comasy.service.ResidentService;
import pds.comasy.service.VisitorService;

import java.util.List;

@RestController
@RequestMapping("/reception")
public class ReceptionController {

    @Autowired
    private ReceptionService receptionService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private ResidentService residentService;

    @GetMapping("/view")
    public ModelAndView reception() {
        ModelAndView model = new ModelAndView();
        model.setViewName("reception/view");
        List<Visitor> visitors = visitorService.getAllVisitors();
        List<ResidentDto> residents = residentService.getAllResidents();

        model.addObject("visitors", visitors);
        model.addObject("residents", residents);

        return model;
    }

    @PostMapping("/notification")
    public ResponseEntity<NotificationVisitor> createNotificationVisitor(@RequestBody NotificationVisitor notificationVisitor) {
        try {
            NotificationVisitor newNotificationVisitor = receptionService.createNotification(notificationVisitor);
            return new ResponseEntity<>(newNotificationVisitor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pending-notifications")
    public ResponseEntity<List<NotificationVisitor>> getPendingNotifications() {
        List<NotificationVisitor> pendingNotifications = receptionService.getPendingNotifications();
        if (pendingNotifications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pendingNotifications, HttpStatus.OK);
    }

    @PutMapping("/notification/{number}/authorize")
    public ResponseEntity<NotificationVisitor> authorizeNotification(@PathVariable("number") Integer number) {
        NotificationVisitor authorizedNotification = receptionService.authorizeNotification(number);
        if (authorizedNotification != null) {
            return new ResponseEntity<>(authorizedNotification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
