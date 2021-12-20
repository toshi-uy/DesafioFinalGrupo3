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
public class HotelBookingDTO {

    private String username;
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFrom;
    private Date dateTo;
    private String destination;
    private String hotelCode;
    private Integer peopleAmount;
    private String roomType;
    private List<PersonDTO> peopleHotel;
    private PaymentMethodDTO paymentmethod;

    public HotelBookingDTO(Date datefrom, Date dateto, String destination, String hotelcode, Integer peopleAmount, String roomType, List<PersonDTO> peopleHotel, PaymentMethodDTO paymentMethod) {
        this.dateFrom = datefrom;
        this.dateTo = dateto;
        this.destination = destination;
        this.hotelCode = hotelcode;
        this.peopleAmount = peopleAmount;
        this.peopleHotel = peopleHotel;
        this.paymentmethod = paymentMethod;
    }
}
