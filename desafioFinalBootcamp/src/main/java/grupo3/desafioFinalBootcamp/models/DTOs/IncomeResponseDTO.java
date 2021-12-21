package grupo3.desafioFinalBootcamp.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class IncomeResponseDTO {

    private Integer month;
    private Integer year;
    private Date date;
    private Double total_income;

    public IncomeResponseDTO(Date date, Double total_income) {
        this.date = date;
        this.total_income = total_income;
    }


    public IncomeResponseDTO( Integer month, Integer year, Double total_income) {
        this.month = month;
        this.year = year;
        this.total_income = total_income;
    }
}
