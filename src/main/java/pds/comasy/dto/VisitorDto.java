package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VisitorDto {

    private Long id;

    private PersonDto person;

    private Integer typeVisitor;

    private String qrCode;
}
