package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.CondominiumDto;
import pds.comasy.entity.Condominium;
import pds.comasy.mapper.CondominiumMapper;
import pds.comasy.service.CondominiumService;

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

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(@RequestBody CondominiumDto condominiumDto) throws Exception {
        String msg = condominiumService.createCondominium(condominiumDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("condominium/list");
        modelAndView.addObject("msg", msg);
        return modelAndView;
    }
}
