package pds.comasy.mapper;

import pds.comasy.dto.ApartmentDto;
import pds.comasy.entity.Apartment;

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
}