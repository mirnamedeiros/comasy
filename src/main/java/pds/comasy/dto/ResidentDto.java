package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pds.comasy.entity.UserAuthentication;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDto {

    private Long id;

    private Integer apartmentNumber;

    private PersonDto person;

    private String cpf_titular;

    private UserAuthentication userAuthentication;
}
