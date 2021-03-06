package grupo3.desafioFinalBootcamp.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private Integer id;
    private String flightNumber;
    private String name;
    private String origin;
    private String destination;
    private String seatType;
    private double flightPrice;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date goingDate;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date returnDate;
    private List<FlightReservationDTO> flightReservations;

    public FlightDTO(String flightNumber, String name, String origin, String destination, String seatType, double flightPrice, Date goingDate, Date returnDate) {
        this.flightNumber = flightNumber;
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.seatType = seatType;
        this.flightPrice = flightPrice;
        this.goingDate = goingDate;
        this.returnDate = returnDate;
    }
}
