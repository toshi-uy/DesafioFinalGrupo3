package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date birthDate;
    private String mail;

    @ManyToMany(mappedBy = "people", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reservation> flightReservationList;

    @ManyToMany(mappedBy = "people", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Booking> hotelBookingList;

    public Person(String dni, String name, String lastname, Date birthDate, String mail) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.mail = mail;
    }
}
