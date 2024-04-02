package pds.comasy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.ResidentDto;
import pds.comasy.dto.VisitorDto;
import pds.comasy.entity.Visitor;
import pds.comasy.enums.EnumRole;
import pds.comasy.enums.EnumTypeVisitor;
import pds.comasy.service.QrCodeService;
import pds.comasy.service.VisitorService;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comasy")
public class IndexController {

    private VisitorService visitorService;

    private QrCodeService qrCodeService;

    public IndexController(QrCodeService qrCodeService, VisitorService visitorService) {
        this.qrCodeService = qrCodeService;
        this.visitorService = visitorService;
    }

    @GetMapping("/resident/form")
    public ModelAndView residentForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resident/form");

        ResidentDto residentDto = new ResidentDto();
        modelAndView.addObject("residentDto", residentDto);

        List<EnumRole> roles = Arrays.asList(EnumRole.values());
        modelAndView.addObject("roles", roles);

        return modelAndView;
    }
    @GetMapping("/visitor/form")
    public ModelAndView visitorForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("visitor/form");

        VisitorDto visitorDto = new VisitorDto();
        modelAndView.addObject("visitorDto", visitorDto);

        List<EnumTypeVisitor> types = Arrays.asList(EnumTypeVisitor.values());
        modelAndView.addObject("types", types);

        return modelAndView;
    }


    @GetMapping("/visitor/{id}")
    public ModelAndView visitorPage(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("visitor/view");

        try {
            VisitorDto visitor = visitorService.getVisitorById(id);
            if (visitor != null) {
                modelAndView.addObject("visitor", visitor);

                // Gere o QR Code a partir do texto
                byte[] qrCodeImage = qrCodeService.generateQRCode(visitor.getQrCode(), 200, 200);
                String qrCodeImageData = Base64.getEncoder().encodeToString(qrCodeImage);
                modelAndView.addObject("qrCodeImage", qrCodeImageData);
                modelAndView.addObject("qrCodeText", visitor.getQrCode());
            } else {
                modelAndView.addObject("visitor", null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelAndView.addObject("visitor", null);
            // Tratar exceção, se necessário
        }

        return modelAndView;
    }
}
