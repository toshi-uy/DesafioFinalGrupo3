package grupo3.desafioFinalBootcamp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelBookingDTO {

    private String username;
    private BookingDTO booking;
}
