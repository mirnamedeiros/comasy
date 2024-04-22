package pds.comasy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pds.comasy.dto.PersonDto;
import pds.comasy.dto.ResidentDto;
import pds.comasy.entity.Resident;
import pds.comasy.mapper.ResidentMapper;
import pds.comasy.repository.ResidentRepository;

import java.util.List;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    private final PersonService personService;

    public ResidentService(ResidentRepository residentRepository, PersonService personService) {
        this.residentRepository = residentRepository;
        this.personService = personService;
    }

    @Transactional
    public ResidentDto createResident(ResidentDto residentDto) throws Exception {
        if (!personService.personExists(residentDto.getPerson().getCpf())) {
            personService.createPerson(residentDto.getPerson());
        } else {
            throw new Exception("Resident already exists");
        }

        List<PersonDto> dependents = residentDto.getDependents();
        if (dependents != null && !dependents.isEmpty()) {
            for (PersonDto dependent : dependents) {
                if (!personService.personExists(dependent.getCpf())) {
                    personService.createPerson(dependent);
                }
            }
        }

        Resident savedResident = residentRepository.save(ResidentMapper.mapToResident(residentDto));

        return ResidentMapper.mapToResidentDto(savedResident);
    }

    @Transactional(readOnly = true)
    public ResidentDto getResidentById(Long id) throws Exception {
        Resident resident = residentRepository.findById(id)
            .orElseThrow(() -> new Exception("Resident not found"));

        return ResidentMapper.mapToResidentDto(resident);
    }

    @Transactional(readOnly = true)
    public List<ResidentDto> getAllResidents() {
        List<Resident> residents = residentRepository.findAll();
        return ResidentMapper.mapToResidentDtoList(residents);
    }

    @Transactional
    public ResidentDto updateResident(Long id, ResidentDto residentDto) throws Exception {
        Resident existingResident = residentRepository.findById(id)
            .orElseThrow(() -> new Exception("Resident not found"));
        Resident updatedResident = ResidentMapper.mapToResident(residentDto);
        updatedResident.setId(existingResident.getId());

        Resident savedResident = residentRepository.save(updatedResident);
        return ResidentMapper.mapToResidentDto(savedResident);
    }

    @Transactional
    public void deleteResident(Long id) throws Exception {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new Exception("Resident not found"));

        residentRepository.delete(resident);
    }

//    private void validateRole(RoleDto roleDto) throws Exception {
//        if (roleDto == null || roleDto.getId() == null) {
//            throw new Exception("Role is required");
//        }

//         Implemente a lógica de validação necessária, por exemplo:
//         Verificar se o papel é um dos papéis permitidos no sistema
//    }
}
