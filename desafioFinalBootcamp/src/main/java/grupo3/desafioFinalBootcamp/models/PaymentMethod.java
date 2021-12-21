package grupo3.desafioFinalBootcamp.models;

import lombok.*;

import javax.persistence.*;

@Data
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
