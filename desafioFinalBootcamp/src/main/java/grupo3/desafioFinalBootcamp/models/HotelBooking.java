package grupo3.desafioFinalBootcamp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
public class HotelBooking {

    private String username;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Nullable
    private Date bookingDate;
    private Double price;

    public HotelBooking(String username, Booking booking, @Nullable Date bookingDate, Double price) {
        this.username = username;
        this.booking = booking;
        this.bookingDate = bookingDate;
        this.price = price;
    }
}
