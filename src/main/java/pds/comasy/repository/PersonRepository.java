package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pds.comasy.entity.Person;

public interface PersonRepository extends JpaRepository<Person, String> {


}
