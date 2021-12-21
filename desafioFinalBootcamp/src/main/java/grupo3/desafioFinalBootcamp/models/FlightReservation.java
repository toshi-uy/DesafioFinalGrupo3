package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight_reservations")
public class FlightReservation {

    @Id
    private Integer id;
    private String userName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date goingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date returnDate;

    private String origin;
    private String destination;
    private String flightNumber;
    private Integer seats;
    private String seatType;

    @ManyToMany
    @JoinTable(
            name = "reservation_people",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id")
    )
    private List<Person> peopleFlight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")

    private PaymentMethod paymentMethod;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date bookingDate;
    private Double price;


    public FlightReservation(String userName, Date goingDate, Date returnDate, String origin, String destination, String flightNumber, Integer seats, String seatType, List<Person> peopleFlight, PaymentMethod paymentMethod, Date bookingDate) {
        this.userName = userName;
        this.goingDate = goingDate;
        this.returnDate = returnDate;
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.seats = seats;
        this.seatType = seatType;
        this.peopleFlight = peopleFlight;
        this.paymentMethod = paymentMethod;
    }
}
