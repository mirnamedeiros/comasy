package pds.comasy.mapper;

import pds.comasy.dto.ApartmentDto;
import pds.comasy.dto.CondominiumDto;
import pds.comasy.entity.Apartment;
import pds.comasy.entity.Condominium;

import java.util.ArrayList;
import java.util.List;

public class CondominiumMapper {

    public static Condominium mapToCondominium(CondominiumDto condominiumDto) {
        Condominium condominium = new Condominium();
        condominium.setId(condominiumDto.getId());
        condominium.setName(condominiumDto.getName());
        condominium.setCity(condominiumDto.getCity());
        condominium.setCnpj(condominiumDto.getCnpj());
        condominium.setState(condominiumDto.getState());
        condominium.setNeighborhood(condominiumDto.getNeighborhood());
        condominium.setStreetAddress(condominiumDto.getStreetAddress());
        condominium.setTelephoneNumber(condominiumDto.getTelephoneNumber());
        condominium.setZipCode(condominiumDto.getZipCode());

        List<Apartment> apartmentList = new ArrayList<>();
        if (condominiumDto.getApartmentList() != null) {
            for (ApartmentDto apartmentDto : condominiumDto.getApartmentList()) {
                apartmentList.add(ApartmentMapper.mapToApartment(apartmentDto));
            }
            condominium.setApartmentList(apartmentList);
        }

        return condominium;
    }

    public static CondominiumDto mapToCondominiumDto(Condominium condominium) {
        CondominiumDto condominiumDto = new CondominiumDto();
        condominiumDto.setId(condominium.getId());
        condominiumDto.setName(condominium.getName());
        condominiumDto.setCity(condominium.getCity());
        condominiumDto.setCnpj(condominium.getCnpj());
        condominiumDto.setState(condominium.getState());
        condominiumDto.setNeighborhood(condominium.getNeighborhood());
        condominiumDto.setStreetAddress(condominium.getStreetAddress());
        condominiumDto.setTelephoneNumber(condominium.getTelephoneNumber());
        condominiumDto.setZipCode(condominium.getZipCode());

        if(condominium.getApartmentList() != null) {
            List<ApartmentDto> apartmentDtoList = new ArrayList<>();
            for (Apartment apartment : condominium.getApartmentList()) {
                apartmentDtoList.add(ApartmentMapper.mapToApartmentDto(apartment));
            }
            condominiumDto.setApartmentList(apartmentDtoList);
        }

        return condominiumDto;
    }
}
