package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.ApartmentDto;
import pds.comasy.service.ApartmentService;

import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("apartment/form");
        modelAndView.addObject("apartment", new ApartmentDto());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listApartment(Model model) {
        List<ApartmentDto> apartmentDtoList = apartmentService.listApartment();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model.addAttribute("list", apartmentDtoList));
        modelAndView.setViewName("apartment/list");
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public ModelAndView createdApartment(@ModelAttribute("apartment") ApartmentDto apartmentDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/apartment/list");
        try {
            apartmentService.createdApartment(apartmentDto);
            modelAndView.addObject("msg", "Apartamento cadastrado com sucesso!");
        } catch (Exception e) {
            modelAndView.addObject("msg", e.getMessage());
        }
        return modelAndView;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApartmentDto> updateApartment(@PathVariable Long id, @RequestBody ApartmentDto apartmentDto) throws Exception {
        ApartmentDto updateApartment = apartmentService.updateApartment(id, apartmentDto);
        return ResponseEntity.ok(updateApartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable Long id) throws Exception {
        apartmentService.deleteApartment(id);
        return ResponseEntity.noContent().build();
    }
}
