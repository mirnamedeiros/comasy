package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.comasy.entity.NotificationVisitor;

import java.util.List;

@Repository
public interface NotificationVisitorRepository extends JpaRepository<NotificationVisitor, Integer> {

    List<NotificationVisitor> findByAuthorizedFalse();

    NotificationVisitor findByNumber(Integer number);
}
