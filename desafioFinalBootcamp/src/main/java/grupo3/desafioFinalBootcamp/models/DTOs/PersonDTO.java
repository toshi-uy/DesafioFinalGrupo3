package grupo3.desafioFinalBootcamp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Integer id;
    private String dni;
    private String name;
    private String lastname;
    private Date birthDate;
    private String mail;
    private List<FlightReservationDTO> flightReservationList;
    private List<HotelBookingDTO> hotelBookingList;

    public PersonDTO(String dni, String name, String lastname, Date birthDate, String mail) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.mail = mail;
    }
}
