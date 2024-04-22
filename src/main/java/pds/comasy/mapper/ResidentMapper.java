package pds.comasy.mapper;

import pds.comasy.dto.PersonDto;
import pds.comasy.dto.ResidentDto;
import pds.comasy.entity.Person;
import pds.comasy.entity.Resident;

import java.util.ArrayList;
import java.util.List;

public class ResidentMapper {

    public static Resident mapToResident(ResidentDto residentDto) {
        Resident resident = new Resident();
        resident.setId(residentDto.getId());
        resident.setApartmentNumber(residentDto.getApartmentNumber());
        resident.setRoleId(residentDto.getRole());
        resident.setPerson(PersonMapper.mapToPerson(residentDto.getPerson()));

        List<Person> dependents = new ArrayList<>();
        if(residentDto.getDependents() != null) {
            for (PersonDto dependentDto : residentDto.getDependents()) {
                dependents.add(PersonMapper.mapToPerson(dependentDto));
            }
            resident.setDependents(dependents);
        }

        return resident;
    }

    public static ResidentDto mapToResidentDto(Resident resident) {
        ResidentDto residentDto = new ResidentDto();
        residentDto.setId(resident.getId());
        residentDto.setApartmentNumber(resident.getApartmentNumber());
        residentDto.setRole(resident.getRoleId());
        residentDto.setPerson(PersonMapper.mapToPersonDto(resident.getPerson()));

        if(resident.getDependents() != null) {
            List<PersonDto> dependents = new ArrayList<>();
            for (Person dependent : resident.getDependents()) {
                dependents.add(PersonMapper.mapToPersonDto(dependent));
            }
            residentDto.setDependents(dependents);
        }

        return residentDto;
    }

    public static List<ResidentDto> mapToResidentDtoList(List<Resident> residents) {
        List<ResidentDto> residentDtos = new ArrayList<>();
        for (Resident resident : residents) {
            residentDtos.add(mapToResidentDto(resident));
        }
        return residentDtos;
    }
}
