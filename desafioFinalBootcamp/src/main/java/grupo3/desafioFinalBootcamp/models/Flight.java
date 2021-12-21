package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    public Flight(String flightNumber, String name, String origin, String destination, String seatType, double flightPrice, Date goingDate, Date returnDate) {
        this.flightNumber = flightNumber;
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.seatType = seatType;
        this.flightPrice = flightPrice;
        this.goingDate = goingDate;
        this.returnDate = returnDate;
    }

    public Flight(Integer id, String flightNumber, String name, String origin, String destination, String seatType, double flightPrice, Date goingDate, Date returnDate) {
        this.id = id;
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
