package grupo3.desafioFinalBootcamp.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import grupo3.desafioFinalBootcamp.models.BookResId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class PackageDTO {
    private Integer packageNumber;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date creation_date;
    private Integer client_id;
    private BookResId bookingsOrReservations;
}
