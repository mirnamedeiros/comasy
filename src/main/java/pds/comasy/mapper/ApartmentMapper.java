package pds.comasy.mapper;

import pds.comasy.dto.ApartmentDto;
import pds.comasy.entity.Apartment;

import java.util.ArrayList;
import java.util.List;

public class ApartmentMapper {

    public static Apartment mapToApartment(ApartmentDto apartmentDto) {
        Apartment apartment = new Apartment();
        apartment.setId(apartmentDto.getId());
        apartment.setNumber(apartmentDto.getNumber());
        apartment.setBlock(apartmentDto.getBlock());
        apartment.setResidentOwnerCpf(apartmentDto.getResidentOwnerCpf());

        return apartment;
    }

    public static ApartmentDto mapToApartmentDto(Apartment apartment) {
        ApartmentDto apartmentDto = new ApartmentDto();
        apartmentDto.setId(apartment.getId());
        apartmentDto.setNumber(apartment.getNumber());
        apartmentDto.setBlock(apartment.getBlock());
        apartmentDto.setResidentOwnerCpf(apartment.getResidentOwnerCpf());

        return apartmentDto;
    }

    public static List<Apartment> mapToListApartment(List<ApartmentDto> apartmentDtoList) {
       List<Apartment> apartmentList = new ArrayList<>();

       if(apartmentDtoList != null) {
           for(ApartmentDto apartmentDto : apartmentDtoList) {
               apartmentList.add(ApartmentMapper.mapToApartment(apartmentDto));
           }
       }
       return apartmentList;
    }

    public static List<ApartmentDto> mapToListApartmentDto(List<Apartment> apartmentList) {
        List<ApartmentDto> apartmentDtoList = new ArrayList<>();

        if(apartmentList != null) {
            for(Apartment apartment : apartmentList) {
                apartmentDtoList.add(ApartmentMapper.mapToApartmentDto(apartment));
            }
        }
        return apartmentDtoList;
    }
}