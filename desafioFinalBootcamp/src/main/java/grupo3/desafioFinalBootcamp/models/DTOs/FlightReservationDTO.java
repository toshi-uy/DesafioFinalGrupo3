package grupo3.desafioFinalBootcamp.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightReservationDTO {

    private String username;
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date goingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date returnDate;
    private String origin;
    private String destination;
    private String flightNumber;
    private Integer seats;
    private String seatType;
    private List<PersonDTO> peopleFlight;
    private PaymentMethodDTO paymentMethod;

    public FlightReservationDTO(String username, Date goingDate, Date returnDate, String origin, String destination, String flightNumber, Integer seats, String seatType, List<PersonDTO> peopleFlight, PaymentMethodDTO paymentMethod, FlightDTO flight) {
        this.username = username;
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
