package grupo3.desafioFinalBootcamp.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
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

public class HotelDTO {
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
    private List<HotelBookingDTO> hotelBooking_hotel;

    public HotelDTO(String hotelCode, String name, String destination, String roomType, int priceByNight, Date dateFrom, Date dateTo, boolean booked) {
        this.hotelCode = hotelCode;
        this.name = name;
        this.destination = destination;
        this.roomType = roomType;
        this.priceByNight = priceByNight;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.booked = booked;
    }
}
