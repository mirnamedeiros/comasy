package pds.comasy.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pds.comasy.entity.UserAuthentication;
import pds.comasy.repository.UserAuthenticationRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserAuthenticationRepository userAuthenticationRepository;

    public AuthorizationService(UserAuthenticationRepository userAuthenticationRepository) {
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    @Override
    public UserAuthentication loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthentication user = userAuthenticationRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
