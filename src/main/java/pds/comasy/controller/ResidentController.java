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
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.ResidentDto;
import pds.comasy.enums.EnumRole;
import pds.comasy.service.ResidentService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resident")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/form")
    public ModelAndView residentForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resident/form");

        ResidentDto residentDto = new ResidentDto();
        modelAndView.addObject("residentDto", residentDto);

        List<EnumRole> roles = Arrays.asList(EnumRole.values());
        modelAndView.addObject("roles", roles);

        return modelAndView;
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
