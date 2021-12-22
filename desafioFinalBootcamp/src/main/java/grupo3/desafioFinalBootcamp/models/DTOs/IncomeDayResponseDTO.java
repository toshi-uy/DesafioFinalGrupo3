package grupo3.desafioFinalBootcamp.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IncomeDayResponseDTO {

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date date;
    private double total_income;

    public IncomeDayResponseDTO(Date date, double total_income) {
        this.date = date;
        this.total_income = total_income;
    }



}
