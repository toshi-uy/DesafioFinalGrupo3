package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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
    private String place;
    private String roomType;
    private int roomPrice;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date disponibilityDateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date disponibilityDateTo;
    private Boolean isBooking;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelBooking> hotelBooking_hotel;

    public Hotel(String hotelCode, String name, String place, String roomType, int roomPrice, Date disponibilityDateFrom, Date disponibilityDateTo, boolean isBooking) {
        this.hotelCode = hotelCode;
        this.name = name;
        this.place = place;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.disponibilityDateFrom = disponibilityDateFrom;
        this.disponibilityDateTo = disponibilityDateTo;
        this.isBooking = isBooking;
    }

    public Hotel(Integer id, String hotelCode, String name, String place, String roomType, int roomPrice, Date disponibilityDateFrom, Date disponibilityDateTo, Boolean isBooking) {
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
}
