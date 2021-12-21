package grupo3.desafioFinalBootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookResId {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer bookResId1;
    private Integer bookResId2;

    @OneToOne(mappedBy = "bookingsOrReservationsId")
    private Package aPackage;

    public BookResId(Integer bookResId1, Integer bookResId2) {
        this.bookResId1 = bookResId1;
        this.bookResId2 = bookResId2;
    }
}
