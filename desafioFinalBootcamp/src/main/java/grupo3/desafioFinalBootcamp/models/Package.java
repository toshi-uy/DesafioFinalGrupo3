package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_resId", referencedColumnName = "id")
    @JsonIgnore
    private BookResId bookingsOrReservationsId;
}
