package pds.comasy.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pds.comasy.dto.RegisterDto;
import pds.comasy.dto.UserAuthenticationDto;
import pds.comasy.exceptions.AuthenticationInvalidException;
import pds.comasy.exceptions.EntityAlreadyExistsException;
import pds.comasy.exceptions.InvalidFieldException;
import pds.comasy.service.AuthenticationService;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated UserAuthenticationDto data, HttpServletResponse response) {
        try {
            String token = authenticationService.authenticate(data);

            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // 1 dia de validade
            response.addCookie(cookie);
        } catch (InvalidFieldException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        } catch (AuthenticationInvalidException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "An internal server error occurred."));
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDto data) {

        try {
            authenticationService.register(data);
        } catch (EntityAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", e.getMessage()));
        }
        return ResponseEntity.ok().build();
    }
}
