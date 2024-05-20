package pds.comasy.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pds.comasy.entity.UserAuthentication;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;

@Component
public class AuthProviderService extends AuthProviderService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        String login = auth.getName();
        String senha = auth.getCredentials().toString();

        Usuario usuario = usuarioService.findByLogin(login);

        if (usuario != null) {
            if (verificarSenha(usuario, senha)) {
                Collection<? extends GrantedAuthority> authorities = usuario.getPermissoes();
                return new UsernamePasswordAuthenticationToken(usuario, senha, authorities);
            } else {
                throw new UsernameNotFoundException("Login e/ou senha inválidos.");
            }
        }
        throw new UsernameNotFoundException("Login e/ou senha inválidos.");
    }
}