package grupo3.desafioFinalBootcamp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {
    private Integer id;
    private String type;
    private String number;
    private int dues;

    public PaymentMethodDTO(String type, String number, int dues) {
        this.type = type;
        this.number = number;
        this.dues = dues;
    }
}
