package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.CondominiumDto;
import pds.comasy.exceptions.InvalidFieldException;
import pds.comasy.service.CondominiumService;

import java.util.List;

@RestController
@RequestMapping("/condominium")
public class CondominiumController {

    @Autowired
    private CondominiumService condominiumService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("condominium/form");
        modelAndView.addObject("condominium", new CondominiumDto());
        return modelAndView;
    }

    @GetMapping("/listar")
    public ModelAndView listCondominium(Model model) {
        List<CondominiumDto> condominiumDtoList = condominiumService.listCondominium();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model.addAttribute("list", condominiumDtoList));
        modelAndView.setViewName("condominium/list");
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public ModelAndView createdCondominium(@ModelAttribute("condominium") CondominiumDto condominiumDto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            CondominiumDto condominium = condominiumService.createCondominium(condominiumDto);
            modelAndView.setViewName("redirect:/condominium/listar");
            modelAndView.addObject("msg", "Condomínio cadastrado com sucesso!");
        } catch (InvalidFieldException e) {
            modelAndView.addObject("msg", e.getMessage());
        } catch (Exception e) {
            modelAndView.addObject("msg", e.getMessage());
        }
        return modelAndView;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominiumDto> updateCondominium(@PathVariable Long id, @RequestBody CondominiumDto condominiumDto) throws Exception {
        CondominiumDto updateCondominium = condominiumService.updateCondominium(id, condominiumDto);
        return ResponseEntity.ok(updateCondominium);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondominium(@PathVariable Long id) throws Exception {
        condominiumService.deleteCondominium(id);
        return ResponseEntity.noContent().build();
    }
}
