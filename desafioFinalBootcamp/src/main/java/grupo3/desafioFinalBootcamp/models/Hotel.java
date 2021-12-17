package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String hotelCode;
    private String name;
    private String destination;
    private String roomType;
    private int priceByNight;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dateTo;
    private boolean booked;

    public Hotel(String hotelCode, String name, String destination, String roomType, int priceByNight, Date dateFrom, Date dateTo, boolean booked) {
        this.hotelCode = hotelCode;
        this.name = name;
        this.destination = destination;
        this.roomType = roomType;
        this.priceByNight = priceByNight;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.booked = booked;
    }

    @OneToMany(mappedBy = "hotel")
    private List<HotelBooking> hotelBooking_hotel;
}
