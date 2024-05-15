package pds.comasy.mapper;

import pds.comasy.dto.ResidentDto;
import pds.comasy.entity.Resident;

import java.util.ArrayList;
import java.util.List;

public class ResidentMapper {

    public static Resident mapToResident(ResidentDto residentDto) {
        Resident resident = new Resident();
        resident.setId(residentDto.getId());
        resident.setApartmentNumber(residentDto.getApartmentNumber());
        resident.setCpf_titular(residentDto.getCpf_titular());
        resident.setPerson(PersonMapper.mapToPerson(residentDto.getPerson()));
        resident.setUserAuthentication(residentDto.getUserAuthentication());

        return resident;
    }

    public static ResidentDto mapToResidentDto(Resident resident) {
        ResidentDto residentDto = new ResidentDto();
        residentDto.setId(resident.getId());
        residentDto.setApartmentNumber(resident.getApartmentNumber());
        residentDto.setCpf_titular(resident.getCpf_titular());
        residentDto.setPerson(PersonMapper.mapToPersonDto(resident.getPerson()));
        residentDto.setUserAuthentication(resident.getUserAuthentication());

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
