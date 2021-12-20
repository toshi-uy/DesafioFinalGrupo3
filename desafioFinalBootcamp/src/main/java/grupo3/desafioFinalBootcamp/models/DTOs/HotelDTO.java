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
    private String place;
    private String roomType;
    private int roomPrice;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date disponibilityDateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date disponibilityDateTo;
    private Boolean isBooking;
    private List<HotelBookingDTO> hotelBooking_hotel;

    public HotelDTO(Integer id, String hotelCode, String name, String place, String roomType, int roomPrice, Date disponibilityDateFrom, Date disponibilityDateTo, Boolean isBooking) {
        this.id = id;
        this.hotelCode = hotelCode;
        this.name = name;
        this.place = place;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.disponibilityDateFrom = disponibilityDateFrom;
        this.disponibilityDateTo = disponibilityDateTo;
        this.isBooking = isBooking;
    }

    public HotelDTO(String hotelCode, String name, String place, String roomType, int roomPrice, Date disponibilityDateFrom, Date disponibilityDateTo, boolean isBooking) {
        this.hotelCode = hotelCode;
        this.name = name;
        this.place = place;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.disponibilityDateFrom = disponibilityDateFrom;
        this.disponibilityDateTo = disponibilityDateTo;
        this.isBooking = isBooking;
    }
}
