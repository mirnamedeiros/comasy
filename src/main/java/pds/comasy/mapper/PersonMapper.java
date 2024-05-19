package pds.comasy.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import pds.comasy.dto.PersonDto;
import pds.comasy.entity.Person;

import java.io.IOException;

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

    public static PersonDto mapJsonToPersonDto(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, PersonDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
