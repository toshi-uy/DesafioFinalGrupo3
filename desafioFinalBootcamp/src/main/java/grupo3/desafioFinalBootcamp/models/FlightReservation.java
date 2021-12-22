package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight_reservation")
public class FlightReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date bookingDate;
    private Double price;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation flightReservation;

    public FlightReservation(String userName, Reservation flightReservation, Date bookingDate, Double price) {
        this.userName = userName;
        this.flightReservation = flightReservation;
        this.bookingDate = bookingDate;
        this.price = price;
    }
}
