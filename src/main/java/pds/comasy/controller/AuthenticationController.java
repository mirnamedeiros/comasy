package pds.comasy.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
import pds.comasy.repository.UserAuthenticationRepository;
import pds.comasy.service.TokenService;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

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
    public ResponseEntity<?> login(@RequestBody @Validated UserAuthenticationDto data, HttpServletResponse response) throws Exception {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((UserAuthentication) auth.getPrincipal());

            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // 1 dia de validade
            response.addCookie(cookie);
        } catch (Exception e) {
            //TODO fazer exception personalizada
            throw new Exception("Error");
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto data){
        if(userAuthenticationRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        EnumRole role = EnumRole.valueOf(data.getRole().toUpperCase());
        UserAuthentication newUser = new UserAuthentication(data.getUsername(), encryptedPassword, role);

        this.userAuthenticationRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
