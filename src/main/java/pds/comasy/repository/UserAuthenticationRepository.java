package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import pds.comasy.entity.UserAuthentication;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {

    UserDetails findByUsername(String username);
}
