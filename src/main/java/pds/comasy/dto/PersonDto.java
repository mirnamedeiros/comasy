package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
public class PersonDto {

    private String cpf;

    private String name;

    private Date birthday;

    private List<String> phoneNumber;

    private String cnh;

    public PersonDto() {}
}
