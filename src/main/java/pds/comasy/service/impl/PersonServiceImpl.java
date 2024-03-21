package pds.comasy.service.impl;

import org.springframework.stereotype.Service;
import pds.comasy.dto.PersonDto;
import pds.comasy.entity.Person;
import pds.comasy.mapper.PersonMapper;
import pds.comasy.repository.PersonRepository;
import pds.comasy.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = PersonMapper.mapToPerson(personDto);
        return PersonMapper.mapToPersonDto(personRepository.save(person));
    }
}
