package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.comasy.entity.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
