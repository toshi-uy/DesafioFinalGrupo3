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
@Table(name = "hotel_bookings")
public class HotelBooking {

    String username;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    private List<Person> peopleHotel;

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


    public HotelBooking(Date datefrom, Date dateto, String destination, String hotelcode, Integer peopleAmount, String roomType, List<Person> peopleHotel, PaymentMethod paymentMethod) {
        this.dateFrom = datefrom;
        this.dateTo = dateto;
        this.destination = destination;
        this.hotelCode = hotelcode;
        this.peopleAmount = peopleAmount;
        this.peopleHotel = peopleHotel;
        this.paymentmethod = paymentMethod;
    }
}
