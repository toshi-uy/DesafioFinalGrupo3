package grupo3.desafioFinalBootcamp.models.DTOs;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
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
