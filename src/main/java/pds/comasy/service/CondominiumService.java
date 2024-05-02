package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.CondominiumDto;
import pds.comasy.dto.PersonDto;
import pds.comasy.entity.Condominium;
import pds.comasy.entity.Person;
import pds.comasy.entity.Resident;
import pds.comasy.mapper.CondominiumMapper;
import pds.comasy.mapper.PersonMapper;
import pds.comasy.repository.CondominiumRepository;

import java.util.List;

@Service
public class CondominiumService {

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Transactional
    public CondominiumDto createCondominium(CondominiumDto condominiumDto) throws Exception {
        validarCampos(condominiumDto);
        Condominium condominium = CondominiumMapper.mapToCondominium(condominiumDto);
        condominiumRepository.save(condominium);
        return CondominiumMapper.mapToCondominiumDto(condominium);
    }

    public List<CondominiumDto> listCondominium() {
        List<CondominiumDto> condominiumDtoList = CondominiumMapper.mapToListCondominiumDto(condominiumRepository.findAll());
        return condominiumDtoList;
    }

    public void deleteCondominium(Long id) throws Exception {
        Condominium condominium = condominiumRepository.findById(id).orElseThrow(() -> new Exception("Condominiun not found"));
        condominiumRepository.deleteById(id);
    }

    public CondominiumDto updateCondominium(Long id, CondominiumDto condominiumDto) throws Exception {
        Condominium existingCondominium = condominiumRepository.findById(id).orElseThrow(() -> new Exception("Condominium not found"));
        Condominium updateCondominium = CondominiumMapper.mapToCondominium(condominiumDto);
        updateCondominium.setId(existingCondominium.getId());

        Condominium savedCondominium = condominiumRepository.save(updateCondominium);
        return CondominiumMapper.mapToCondominiumDto(savedCondominium);
    }


    public void validarCampos(CondominiumDto condominiumDto) throws Exception {
        if(condominiumDto.getName() == null || condominiumDto.getName() == "") {
            throw new Exception("Erro no nome de condomínio");
        }

        if(condominiumDto.getCity() == null || condominiumDto.getState() == null
                || condominiumDto.getNeighborhood() == null || condominiumDto.getStreetAddress() == null
                || condominiumDto.getState() == null || condominiumDto.getZipCode() == null) {
            throw new Exception("Erro no endereço");
        }

        if(condominiumDto.getCnpj() == null) {
            throw new Exception("Erro no cnpj");
        }
    }
}
