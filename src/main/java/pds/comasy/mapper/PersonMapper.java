package pds.comasy.mapper;

import pds.comasy.dto.PersonDto;
import pds.comasy.entity.Person;

public class PersonMapper {

    public static Person mapToPerson(PersonDto personDto) {
        return new Person(
            personDto.getCpf(),
            personDto.getName(),
            personDto.getBirthday(),
            personDto.getPhoneNumber(),
            personDto.getCnh()
        );
    }

    public static PersonDto mapToPersonDto(Person person) {
        return new PersonDto(
            person.getCpf(),
            person.getName(),
            person.getBirthday(),
            person.getPhoneNumber(),
            person.getCnh()
        );
    }
}
