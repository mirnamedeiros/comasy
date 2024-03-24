package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@AllArgsConstructor
public class ResidentDto {

    private Long id;

    private Integer apartmentNumber;

    private PersonDto person;

    private List<PersonDto> dependents;

    public ResidentDto() {}
}
