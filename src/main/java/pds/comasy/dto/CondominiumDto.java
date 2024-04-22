package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@AllArgsConstructor
public class CondominiumDto {

    private Long id;

    private String name;

    private String cnpj;

    private int telephoneNumber;

    private String zipCode;

    private String streetAddress;

    private String neighborhood;

    private String city;

    private String state;

    List<ApartmentDto> apartmentList;

    public CondominiumDto(){}
}
