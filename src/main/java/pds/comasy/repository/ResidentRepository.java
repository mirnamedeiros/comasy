package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pds.comasy.entity.Resident;

import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    UserDetails findByUserAuthentication_Username(String username);
}
