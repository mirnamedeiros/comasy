package pds.comasy.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.comasy.dto.ApartmentDto;
import pds.comasy.entity.Apartment;
import pds.comasy.exceptions.EntityAlreadyExistsException;
import pds.comasy.exceptions.InvalidFieldException;
import pds.comasy.exceptions.NotFoundException;
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

    public void deleteApartment(Long id) throws NotFoundException {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Apartment not found"));
        apartmentRepository.deleteById(id);
    }

    public ApartmentDto updateApartment(Long id, ApartmentDto apartmentDto) throws NotFoundException {
        Apartment existingApartment = apartmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Apartment not found"));
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
           throw new InvalidFieldException("O campo bloco não pode ser nulo ou vazio");
        }

        if(apartmentDto.getNumber() <= 0) {
            throw new InvalidFieldException("O campo número não pode ser negativo ou igual a zero");
        }

        if(apartmentDto.getResidentOwnerCpf() == null || apartmentDto.getResidentOwnerCpf().isEmpty()) {
            throw new InvalidFieldException("O campo CPF não pode ser nulo ou vazio");
        }
    }

    public boolean existsApartment(ApartmentDto apartmentDto) {
        return apartmentRepository.existsApartment(apartmentDto.getBlock(), apartmentDto.getNumber(), apartmentDto.getCondominium().getId());
    }
}
