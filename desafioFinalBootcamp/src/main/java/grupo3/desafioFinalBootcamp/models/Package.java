package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@Entity
public class Package {
    @Id
    private Integer packageNumber;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date creationDate;
    private Integer clientId;
    @OneToOne
    @JoinColumn(name = "book_resId", referencedColumnName = "id")
    private BookResId bookingsOrReservationsId;
}
