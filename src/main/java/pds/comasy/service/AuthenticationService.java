package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pds.comasy.dto.RegisterDto;
import pds.comasy.dto.UserAuthenticationDto;
import pds.comasy.entity.UserAuthentication;
import pds.comasy.enums.EnumRole;
import pds.comasy.exceptions.AuthenticationInvalidException;
import pds.comasy.exceptions.EntityAlreadyExistsException;
import pds.comasy.exceptions.InvalidFieldException;
import pds.comasy.repository.UserAuthenticationRepository;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    public String authenticate(UserAuthenticationDto data) throws InvalidFieldException, AuthenticationInvalidException {
        validateLoginFields(data);

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        try {
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);
            return tokenService.generateToken((UserAuthentication) auth.getPrincipal());
        } catch (Exception e) {
            throw new AuthenticationInvalidException();
        }
    }

    public void register(RegisterDto data) throws EntityAlreadyExistsException {
        if (userAuthenticationRepository.findByUsername(data.getUsername()) != null) {
            throw new EntityAlreadyExistsException("User already exists.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        EnumRole role = EnumRole.valueOf(data.getRole().toUpperCase());
        UserAuthentication newUser = new UserAuthentication(data.getUsername(), encryptedPassword, role);

        userAuthenticationRepository.save(newUser);
    }

    private void validateLoginFields(UserAuthenticationDto data) throws InvalidFieldException {
        if (data.getUsername() == null || data.getUsername().isEmpty()) {
            throw new InvalidFieldException("Username field is required.");
        }
        if (data.getPassword() == null || data.getPassword().isEmpty()) {
            throw new InvalidFieldException("Password field is required.");
        }
    }
}
