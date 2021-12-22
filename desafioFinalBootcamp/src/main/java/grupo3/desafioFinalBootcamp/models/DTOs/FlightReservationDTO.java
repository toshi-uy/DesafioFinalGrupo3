package grupo3.desafioFinalBootcamp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightReservationDTO {

    private String userName;
    private ReservationDTO flightReservation;
}
