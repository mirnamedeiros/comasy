package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pds.comasy.entity.UserAuthentication;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {

    UserAuthentication findByUsername(String username);
}
