package pds.comasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import pds.comasy.dto.CondominiumDto;
import pds.comasy.dto.PersonDto;
import pds.comasy.entity.Condominium;
import pds.comasy.entity.Person;
import pds.comasy.mapper.CondominiumMapper;
import pds.comasy.mapper.PersonMapper;
import pds.comasy.repository.CondominiumRepository;

@Service
public class CondominiumService {

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Transactional
    public String createCondominium(CondominiumDto condominiumDto) throws Exception {
        Condominium condominium = CondominiumMapper.mapToCondominium(condominiumDto);
        condominiumRepository.save(condominium);
        return "Condomínio criado com sucesso!";
    }
}
