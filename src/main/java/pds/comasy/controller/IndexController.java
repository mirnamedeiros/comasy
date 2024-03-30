package pds.comasy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.ResidentDto;
import pds.comasy.enums.EnumRole;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/comasy")
public class IndexController {

    @GetMapping("/resident/form")
    public ModelAndView residentForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resident/form");

        ResidentDto residentDto = new ResidentDto();
        modelAndView.addObject("residentDto", residentDto);

        List<EnumRole> roles = Arrays.asList(EnumRole.values());
        modelAndView.addObject("roles", roles);

        return modelAndView;
    }
}
