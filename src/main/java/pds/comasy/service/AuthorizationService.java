package pds.comasy.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pds.comasy.repository.UserAuthenticationRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserAuthenticationRepository userAuthenticationRepository;

    public AuthorizationService(UserAuthenticationRepository userAuthenticationRepository) {
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthenticationRepository.findByUsername(username);
    }
}
