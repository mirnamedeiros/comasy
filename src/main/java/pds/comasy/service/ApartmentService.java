package pds.comasy.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.comasy.dto.ApartmentDto;
import pds.comasy.entity.Apartment;
import pds.comasy.exceptions.EntityAlreadyExistsException;
import pds.comasy.exceptions.InvalidFieldException;
import pds.comasy.mapper.ApartmentMapper;
import pds.comasy.repository.ApartmentRepository;

import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Transactional
    public ApartmentDto createdApartment(ApartmentDto apartmentDto) throws EntityAlreadyExistsException, InvalidFieldException {
        validarCampos(apartmentDto);
        Apartment apartment = ApartmentMapper.mapToApartment(apartmentDto);
        apartmentRepository.save(apartment);
        return ApartmentMapper.mapToApartmentDto(apartment);
    }

    public List<ApartmentDto> listApartment() {
        List<ApartmentDto> apartmentDtoList = ApartmentMapper.mapToListApartmentDto(apartmentRepository.findAll());
        return apartmentDtoList;
    }

    public void deleteApartment(Long id) throws Exception {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new Exception("Apartment not found"));
        apartmentRepository.deleteById(id);
    }

    public ApartmentDto updateApartment(Long id, ApartmentDto apartmentDto) throws Exception {
        Apartment existingApartment = apartmentRepository.findById(id).orElseThrow(() -> new Exception("Apartment not found"));
        Apartment updateApartment = ApartmentMapper.mapToApartment(apartmentDto);
        updateApartment.setId(existingApartment.getId());

        Apartment savedApartment = apartmentRepository.save(updateApartment);
        return ApartmentMapper.mapToApartmentDto(savedApartment);
    }

    public void validarCampos(ApartmentDto apartmentDto) throws EntityAlreadyExistsException, InvalidFieldException {
        if(existsApartment(apartmentDto)) {
            throw new EntityAlreadyExistsException("Não foi possível cadastrar, pois já existe um apartamento com esse número e bloco nesse condomínio!");
        }

        if(apartmentDto.getBlock() == null || apartmentDto.getBlock().isEmpty()) {
           throw new InvalidFieldException("Erro no campo bloco");
        }

        if(apartmentDto.getNumber() <= 0) {
            throw new InvalidFieldException("Erro no campo número");
        }

        if(apartmentDto.getResidentOwnerCpf() == null || apartmentDto.getResidentOwnerCpf().isEmpty()) {
            throw new InvalidFieldException("Erro no campo cpf");
        }
    }

    public boolean existsApartment(ApartmentDto apartmentDto) {
        return apartmentRepository.existsApartment(apartmentDto.getBlock(), apartmentDto.getNumber(), apartmentDto.getCondominium().getId());
    }
}
