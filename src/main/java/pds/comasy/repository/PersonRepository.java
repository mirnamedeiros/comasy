package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.comasy.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {


}
