package grupo3.desafioFinalBootcamp.repositories;

import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE h.disponibilityDateFrom >= :datefrom AND h.disponibilityDateTo <= :dateto AND h.place = :place ")
    List<Hotel> findListedHotels(@Param("datefrom") Date datefrom, @Param("dateto") Date dateto, @Param("place") String place);

    @Query("SELECT h FROM Hotel h WHERE h.hotelCode LIKE :hotelCode")
    Hotel findByHotelCode(@Param("hotelCode") String hotelCode);
}
