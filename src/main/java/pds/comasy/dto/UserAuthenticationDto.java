package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
@AllArgsConstructor
public class UserAuthenticationDto {

    String username;

    String password;

    String role;
}
