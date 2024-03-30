package pds.comasy.controller;

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
import pds.comasy.dto.ResidentDto;
import pds.comasy.service.ResidentService;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public ResponseEntity<ResidentDto> createResident(@RequestBody ResidentDto residentDto) throws Exception {
        ResidentDto createdResident = residentService.createResident(residentDto);
        return new ResponseEntity<>(createdResident, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentDto> getResidentById(@PathVariable Long id) throws Exception {
        ResidentDto residentDto = residentService.getResidentById(id);
        return ResponseEntity.ok(residentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidentDto> updateResident(@PathVariable Long id, @RequestBody ResidentDto residentDto) throws Exception {
        ResidentDto updatedResident = residentService.updateResident(id, residentDto);
        return ResponseEntity.ok(updatedResident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResident(@PathVariable Long id) throws Exception {
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }
}
