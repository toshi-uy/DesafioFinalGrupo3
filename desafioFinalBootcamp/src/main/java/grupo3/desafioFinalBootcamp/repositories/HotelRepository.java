package grupo3.desafioFinalBootcamp.repositories;

import grupo3.desafioFinalBootcamp.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE h.disponibilityDateFrom >= :dateFrom AND h.disponibilityDateTo <= :dateTo AND h.place = :destination ")
    List<Hotel> findListedHotels(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo, @Param("destination") String destination);

    @Query("SELECT h FROM Hotel h WHERE h.hotelCode LIKE :hotelCode")
    Hotel findByHotelCode(@Param("hotelCode") String hotelCode);

    @Query("SELECT h FROM Hotel h WHERE h.id = :id")
    Hotel findHotelById(@Param("id") Integer id);
}
