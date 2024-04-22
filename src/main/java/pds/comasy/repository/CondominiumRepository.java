package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pds.comasy.entity.Condominium;

public interface CondominiumRepository extends JpaRepository<Condominium, Long> {
}
