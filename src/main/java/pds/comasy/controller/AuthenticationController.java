package pds.comasy.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pds.comasy.dto.RegisterDto;
import pds.comasy.dto.UserAuthenticationDto;
import pds.comasy.entity.UserAuthentication;
import pds.comasy.enums.EnumRole;
import pds.comasy.exceptions.AuthenticationInvalidException;
import pds.comasy.exceptions.InvalidFieldException;
import pds.comasy.repository.UserAuthenticationRepository;
import pds.comasy.service.TokenService;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated UserAuthenticationDto data, HttpServletResponse response) {
        try {
            validateLoginFields(data);

            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
            String token = "";
            try {
                Authentication auth = this.authenticationManager.authenticate(usernamePassword);
                token = tokenService.generateToken((UserAuthentication) auth.getPrincipal());
            } catch (Exception e) {
                throw new AuthenticationInvalidException();
            }

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

    private void validateLoginFields(UserAuthenticationDto data) throws InvalidFieldException {
        if (data.getUsername() == null || data.getUsername().isEmpty()) {
            throw new InvalidFieldException("Username field is required.");
        }
        if (data.getPassword() == null || data.getPassword().isEmpty()) {
            throw new InvalidFieldException("Password field is required.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDto data){
        if(userAuthenticationRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        EnumRole role = EnumRole.valueOf(data.getRole().toUpperCase());
        UserAuthentication newUser = new UserAuthentication(data.getUsername(), encryptedPassword, role);

        this.userAuthenticationRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
