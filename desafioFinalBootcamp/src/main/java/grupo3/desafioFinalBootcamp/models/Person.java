package grupo3.desafioFinalBootcamp.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String dni;
    private String name;
    private String lastname;
    private Date birthDate;
    private String mail;

    @ManyToMany(mappedBy = "peopleFlight", cascade = CascadeType.ALL)
    private List<FlightReservation> flightReservationList;

    @ManyToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<HotelBooking> hotelBookingList;

    public Person(String dni, String name, String lastname, Date birthDate, String mail) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.mail = mail;
    }
}
