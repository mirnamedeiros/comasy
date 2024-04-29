package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.CondominiumDto;
import pds.comasy.dto.ResidentDto;
import pds.comasy.entity.Condominium;
import pds.comasy.mapper.CondominiumMapper;
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
    public ResponseEntity<List<CondominiumDto>> listCondominium() {
        List<CondominiumDto> condominiumDtoList = condominiumService.listCondominium();
        return ResponseEntity.ok(condominiumDtoList);
    }

    @PostMapping("/cadastrar")
    public ModelAndView createdCondominium(@RequestBody CondominiumDto condominiumDto) throws Exception {
        String msg = condominiumService.createCondominium(condominiumDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("condominium/list");
        modelAndView.addObject("msg", msg);
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
