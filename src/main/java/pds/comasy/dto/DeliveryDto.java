package pds.comasy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryDto {

    private Integer number;

    private Integer residentId;

    private Date arrivalDate;

    private Boolean delivered;

    private String formattedArrivalDate;

    public void setFormattedArrivalDate(String formattedArrivalDate) {
        this.formattedArrivalDate = formattedArrivalDate;
    }
}