package grupo3.desafioFinalBootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
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

    @ManyToMany(mappedBy = "peopleFlight")
    private List<FlightReservation> flightReservationList;

    @ManyToMany(mappedBy = "peopleHotel")
    private List<HotelBooking> hotelBookingList;

    public Person(String dni, String name, String lastname, Date birthDate, String mail){
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.mail = mail;
    }
}
