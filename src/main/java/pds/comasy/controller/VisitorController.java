package pds.comasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.VisitorDto;
import pds.comasy.enums.EnumTypeVisitor;
import pds.comasy.service.QrCodeService;
import pds.comasy.service.VisitorService;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private QrCodeService qrCodeService;

    @GetMapping("/form")
    public ModelAndView visitorForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("visitor/form");

        VisitorDto visitorDto = new VisitorDto();
        modelAndView.addObject("visitorDto", visitorDto);

        List<EnumTypeVisitor> types = Arrays.asList(EnumTypeVisitor.values());
        modelAndView.addObject("types", types);

        return modelAndView;
    }

    @GetMapping("/qrCode/{id}")
    public ModelAndView visitorPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("visitor/view");

        try {
            VisitorDto visitor = visitorService.getVisitorById(id);
            if (visitor != null) {
                modelAndView.addObject("visitor", visitor);

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
        }

        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<VisitorDto> createVisitor(@RequestBody VisitorDto visitorDto) {
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

    @PostMapping("/verify")
    public ResponseEntity<Map<String, String>> verifyQRCode(@RequestBody String text) {
        System.out.println("Controller: "+text);
        boolean validQrCode = visitorService.verifyQRCode(text);

        Map<String, String> response = new HashMap<>();
        response.put("valid", String.valueOf(validQrCode));
        response.put("message", validQrCode ? "QR Code válido" : "QR Code inválido");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/generate")
    public ResponseEntity<String> generateQrCode(@RequestParam("text") String text) {
        try {
            byte[] qrCodeImage = qrCodeService.generateQRCode(text, 200, 200);
            HttpHeaders headers = new HttpHeaders();
            String qrCodeImageData = Base64.getEncoder().encodeToString(qrCodeImage);
            return new ResponseEntity<>(qrCodeImageData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
