package pds.comasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pds.comasy.entity.Resident;
import pds.comasy.entity.Suggestion;

import java.util.Date;
import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    @Query("SELECT s FROM Suggestion s WHERE s.type = ?1 AND s.message = ?2 AND s.dataProposta = ?3 AND s.resident = ?4")
    Suggestion findByTypeAndMessageAndDataPropostaAndResident(
            String type, String message, Date dataProposta, Resident resident
    );

    List<Suggestion> findByActiveTrue();
}
