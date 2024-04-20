package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.comasy.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
