package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pds.comasy.dto.ResidentDto;
import pds.comasy.entity.Resident;
import pds.comasy.exceptions.EntityAlreadyExistsException;
import pds.comasy.exceptions.FailedToDeleteException;
import pds.comasy.exceptions.NotFoundException;
import pds.comasy.mapper.ResidentMapper;
import pds.comasy.repository.ResidentRepository;
import pds.comasy.repository.UserAuthenticationRepository;

import java.util.List;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    UserAuthenticationRepository userAuthenticationRepository;

    @Transactional
    public ResidentDto createResident(ResidentDto residentDto) throws Exception {
        if (!personService.personExists(residentDto.getPerson().getCpf())) {
            personService.createPerson(residentDto.getPerson());
        } else {
            throw new EntityAlreadyExistsException("Resident already exists");
        }

        if (userAuthenticationRepository.findByUsername(residentDto.getUserAuthentication().getUsername()) != null) {
            throw new Exception("User or password already exists");
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(residentDto.getUserAuthentication().getPassword());
            residentDto.getUserAuthentication().setPassword(encryptedPassword);
            userAuthenticationRepository.save(residentDto.getUserAuthentication());
        }

        Resident savedResident = residentRepository.save(ResidentMapper.mapToResident(residentDto));

        return ResidentMapper.mapToResidentDto(savedResident);
    }

    @Transactional(readOnly = true)
    public ResidentDto getResidentById(Long id) throws NotFoundException {
        Resident resident = residentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Resident not found"));

        return ResidentMapper.mapToResidentDto(resident);
    }

    @Transactional(readOnly = true)
    public List<ResidentDto> getAllResidents() {
        List<Resident> residents = residentRepository.findAll();
        return ResidentMapper.mapToResidentDtoList(residents);
    }

    @Transactional
    public ResidentDto updateResident(Long id, ResidentDto residentDto) throws NotFoundException {
        Resident existingResident = residentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Resident not found"));
        Resident updatedResident = ResidentMapper.mapToResident(residentDto);
        updatedResident.setId(existingResident.getId());

        Resident savedResident = residentRepository.save(updatedResident);
        return ResidentMapper.mapToResidentDto(savedResident);
    }

    @Transactional
    public void deleteResident(Long id) throws FailedToDeleteException {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new FailedToDeleteException("Resident not found"));

        residentRepository.delete(resident);
    }
}
