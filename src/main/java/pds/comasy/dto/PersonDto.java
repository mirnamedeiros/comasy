package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PersonDto {

    private String cpf;

    private String name;

    private Date birthday;

    private List<String> phoneNumber;

    private String cnh;
}
