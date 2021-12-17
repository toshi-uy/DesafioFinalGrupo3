package grupo3.desafioFinalBootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_methods")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;
    private String number;
    private int dues;

    public PaymentMethod(String type, String number, int dues) {
        this.type = type;
        this.number = number;
        this.dues = dues;
    }
}
