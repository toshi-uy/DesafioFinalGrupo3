package grupo3.desafioFinalBootcamp.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IncomeResponseDTO {

    private int month;
    private int year;
    private Date date;
    private double total_income;

    public IncomeResponseDTO(Date date, double total_income) {
        this.date = date;
        this.total_income = total_income;
    }


    public IncomeResponseDTO(int month, int year, double total_income) {
        this.month = month;
        this.year = year;
        this.total_income = total_income;
    }
}
