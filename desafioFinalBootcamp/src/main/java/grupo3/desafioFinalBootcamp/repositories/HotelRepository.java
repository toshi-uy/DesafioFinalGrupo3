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

    @Query("SELECT h FROM Hotel h WHERE h.dateFrom >= :datefrom AND h.dateTo <= :dateto AND h.destination = :destination ")
    List<Hotel> findListedHotels(@Param("datefrom") Date datefrom, @Param("dateto") Date dateto, @Param("destination") String destination);

    @Query("DELETE FROM Hotel h WHERE h.hotelCode = :hotelCode")
    StatusDTO deleteHotelByHotelCode(@Param("hotelCode") String hotelCode);

    @Query("SELECT h FROM Hotel h WHERE h.hotelCode LIKE :hotelCode")
    Hotel findByHotelCode(@Param("hotelCode") String hotelCode);
}
