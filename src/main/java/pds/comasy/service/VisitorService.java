package pds.comasy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pds.comasy.dto.PersonDto;
import pds.comasy.dto.VisitorDto;
import pds.comasy.entity.Visitor;
import pds.comasy.mapper.VisitorMapper;
import pds.comasy.repository.VisitorRepository;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;

    private final PersonService personService;

    public VisitorService(VisitorRepository visitorRepository, PersonService personService) {
        this.visitorRepository = visitorRepository;
        this.personService = personService;
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public VisitorDto getVisitorById(Long id) throws Exception {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new Exception("Visitant not found"));
        return VisitorMapper.mapToVisitorDto(visitor);
    }

    public VisitorDto createVisitor(VisitorDto visitorDto) throws Exception {
        try {
            if (visitorDto.getId() != null && visitorRepository.existsById(visitorDto.getId())) {
                throw new Exception("Visitor already exists.");
            }

            if (!personService.personExists(visitorDto.getPerson().getCpf())) {
                personService.createPerson(visitorDto.getPerson());
            }

            String qrCodeText = VisitorMapper.generateQRCodeText(visitorDto);
            visitorDto.setQrCode(qrCodeText);
            System.out.println(visitorDto.getQrCode());
            Visitor savedVisitor = visitorRepository.save(VisitorMapper.mapToVisitor(visitorDto));
            System.out.println(savedVisitor.getQrCode());
            return VisitorMapper.mapToVisitorDto(savedVisitor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Failed to save visitor");
        }
    }

    public VisitorDto updateVisitor(Long id, VisitorDto visitorDto) throws Exception {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new Exception("Visitor not found"));

        Visitor updatedVisitor = VisitorMapper.mapToVisitor(visitorDto);
        updatedVisitor.setId(visitor.getId());

        String qrCodeText = VisitorMapper.generateQRCodeText(visitorDto);
        updatedVisitor.setQrCode(qrCodeText);

        Visitor savedUpdatedVisitor = visitorRepository.save(updatedVisitor);
        return VisitorMapper.mapToVisitorDto(savedUpdatedVisitor);
    }

    public void deleteVisitor(Long id) throws Exception {
        // Check if visitor exists
        if (!visitorRepository.existsById(id)) {
            throw new Exception("Visitor not found");
        }
        visitorRepository.deleteById(id);
    }

    public boolean verifyQRCode(String qrCodeText) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            VisitorDto visitorDto = mapper.readValue(qrCodeText, VisitorDto.class);

            String personCpf = visitorDto.getPerson().getCpf();

            PersonDto person = personService.getPersonByCpf(personCpf);
            if (personService.getPersonByCpf(personCpf) == null) {
                return false;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String personBirthday = dateFormat.format(person.getBirthday());
            String visitorBirthday = dateFormat.format(visitorDto.getPerson().getBirthday());

            return person.getName().equals(visitorDto.getPerson().getName())
                    && person.getCpf().equals(visitorDto.getPerson().getCpf())
                    && personBirthday.equals(visitorBirthday);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
