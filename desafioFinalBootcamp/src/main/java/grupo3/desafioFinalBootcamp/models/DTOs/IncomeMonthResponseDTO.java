package grupo3.desafioFinalBootcamp.models.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IncomeMonthResponseDTO {

    private int month;
    private int year;
    private double total_income;

    public IncomeMonthResponseDTO(int month, int year, double total_income) {
        this.month = month;
        this.year = year;
        this.total_income = total_income;
    }
}
