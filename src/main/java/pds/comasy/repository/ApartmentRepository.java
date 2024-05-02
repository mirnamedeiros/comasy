package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pds.comasy.entity.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
