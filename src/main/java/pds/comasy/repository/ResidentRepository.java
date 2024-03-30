package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.comasy.entity.Resident;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {

}
