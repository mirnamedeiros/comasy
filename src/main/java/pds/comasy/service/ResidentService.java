package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pds.comasy.config.SecurityConfiguration;
import pds.comasy.dto.ResidentDto;
import pds.comasy.entity.Resident;
import pds.comasy.mapper.ResidentMapper;
import pds.comasy.repository.ResidentRepository;

import java.util.List;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService jwtTokenService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Transactional
    public ResidentDto createResident(ResidentDto residentDto) throws Exception {

        if (!personService.personExists(residentDto.getPerson().getCpf())) {
            personService.createPerson(residentDto.getPerson());
        } else {
            throw new Exception("Resident already exists");
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
