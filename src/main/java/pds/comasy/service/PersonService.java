package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pds.comasy.dto.PersonDto;
import pds.comasy.entity.Person;
import pds.comasy.exceptions.EntityAlreadyExistsException;
import pds.comasy.exceptions.NotFoundException;
import pds.comasy.mapper.PersonMapper;
import pds.comasy.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public PersonDto createPerson(PersonDto personDto) throws EntityAlreadyExistsException {
        if (personExists(personDto.getCpf())) {
            throw new EntityAlreadyExistsException("Person already exists");
        }
        Person person = PersonMapper.mapToPerson(personDto);
        return PersonMapper.mapToPersonDto(personRepository.save(person));
    }

    @Transactional(readOnly = true)
    public PersonDto getPersonByCpf(String cpf) throws NotFoundException {
        Person person = personRepository.findById(cpf).orElseThrow(() ->
            new NotFoundException("Person doesn't exist")
        );
        return PersonMapper.mapToPersonDto(person);
    }

    @Transactional
    public PersonDto updatePerson(PersonDto personDto) throws NotFoundException {
        PersonDto person;
        if(personRepository.findById(personDto.getCpf()).isPresent()) {
            person = personDto;
        } else {
            throw new NotFoundException("Person not found");
        }
        return PersonMapper.mapToPersonDto(personRepository.save(PersonMapper.mapToPerson(person)));
    }

    public boolean personExists(String cpf) {

        return personRepository.existsById(cpf);
    }
}
