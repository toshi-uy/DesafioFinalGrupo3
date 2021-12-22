package grupo3.desafioFinalBootcamp.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "fk_payment_method")
    private Integer id;
    private String type;
    private String number;
    private int dues;

    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    List<Booking> bookingList;

    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    List<Reservation> reservationList;

    public PaymentMethod(String type, String number, int dues) {
        this.type = type;
        this.number = number;
        this.dues = dues;
    }

    public boolean isCredit(){
        if(type.equals("CREDIT"))
            return true;
        else
            return false;
    }

    /**
     *
     * @return calculates the porcentaje of interest depending on the amount of dues.
     */
    public int calcularIntereses(){
        if (dues == 1)
            return 0;
        if (dues <= 3)
            return 5;
        if (dues <= 6)
            return 10;
        if (dues <= 9)
            return 15;
        return 20;
    }
}
