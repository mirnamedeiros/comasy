package pds.comasy.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pds.comasy.dto.VisitorDto;
import pds.comasy.entity.Visitor;

public class VisitorMapper {

    public static Visitor mapToVisitor(VisitorDto visitorDto) {
        Visitor visitor = new Visitor();
        visitor.setPerson(PersonMapper.mapToPerson(visitorDto.getPerson()));
        visitor.setTypeVisitor(visitorDto.getTypeVisitor());
        visitor.setQrCode(visitorDto.getQrCode());
        return visitor;
    }

    public static VisitorDto mapToVisitorDto(Visitor visitor) {
        VisitorDto visitorDto = new VisitorDto();
        visitorDto.setPerson(PersonMapper.mapToPersonDto(visitor.getPerson()));
        visitorDto.setTypeVisitor(visitor.getTypeVisitor());
        visitorDto.setQrCode(visitor.getQrCode());
        return visitorDto;
    }

    public static String generateQRCodeText(VisitorDto visitorDto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(visitorDto);
        } catch (JsonProcessingException e) {
            // handle JSON processing exception
            e.printStackTrace();
            return null;
        }
    }
}