package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class ApartmentDto {

    private Long id;

    private int number;

    private String block;

    private String residentOwnerCpf;

    public ApartmentDto(){}
}
