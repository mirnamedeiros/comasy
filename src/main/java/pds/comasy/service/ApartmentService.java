package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.comasy.dto.ApartmentDto;
import pds.comasy.mapper.ApartmentMapper;
import pds.comasy.repository.ApartamentRepository;

import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    private ApartamentRepository apartamentRepository;

    public List<ApartmentDto> listApartment() {
        List<ApartmentDto> apartmentDtoList = ApartmentMapper.mapToListApartmentDto(apartamentRepository.findAll());
        return apartmentDtoList;
    }
}
