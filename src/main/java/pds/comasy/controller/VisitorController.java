package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pds.comasy.dto.VisitorDto;
import pds.comasy.service.VisitorService;

@RestController
@RequestMapping("/api/visitor")
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public ResponseEntity<VisitorDto> createVisitor(@RequestBody VisitorDto visitorDto) {
        System.out.println(visitorDto.toString());
        try {
            VisitorDto createdVisitor = visitorService.createVisitor(visitorDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVisitor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitorDto> getVisitorById(@PathVariable Long id) throws Exception {
        VisitorDto visitorDto = visitorService.getVisitorById(id);
        return ResponseEntity.ok(visitorDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitorDto> updateVisitor(@PathVariable Long id, @RequestBody VisitorDto visitorDto) {
        try {
            VisitorDto updatedVisitor = visitorService.updateVisitor(id, visitorDto);
            return ResponseEntity.ok(updatedVisitor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {
        try {
            visitorService.deleteVisitor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/verify/{qrCodeText}")
    public ResponseEntity<String> verifyQRCode(@PathVariable String qrCodeText) {
        boolean qrCodeExists = visitorService.verifyQRCode(qrCodeText);

        if (qrCodeExists) {
            return ResponseEntity.ok("QR Code válido");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("QR Code inválido");
        }
    }
}
