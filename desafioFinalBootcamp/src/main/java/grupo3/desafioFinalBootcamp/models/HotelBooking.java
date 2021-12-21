package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import grupo3.desafioFinalBootcamp.controller.ReservationController;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "hotel_bookings")
public class HotelBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String destination;
    private String hotelCode;
    private Integer peopleAmount;
    private String roomType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "booking_people",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "people_id"))
    private List<Person> people;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentmethod;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")


    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    private Double price;


    public HotelBooking(Date datefrom, Date dateto, String destination, String hotelcode, Integer peopleAmount, String roomType, List<Person> people, PaymentMethod paymentMethod) {
        this.dateFrom = datefrom;
        this.dateTo = dateto;
        this.destination = destination;
        this.hotelCode = hotelcode;
        this.peopleAmount = peopleAmount;
        this.people = people;
        this.paymentmethod = paymentMethod;
    }
}
