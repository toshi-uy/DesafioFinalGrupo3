package grupo3.desafioFinalBootcamp.repositories;

import grupo3.desafioFinalBootcamp.models.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {
    @Query("SELECT sum(HB.price) FROM HotelBooking HB WHERE HB.bookingDate = :day")
    Double getHotelBookingSumPerDay(@Param("day") Date day);

    @Query("SELECT HB FROM HotelBooking HB WHERE HB.bookingDate BETWEEN :firstmonthyear AND :lastmonthyear")
    List<HotelBooking> getBookingSumPerMonth(@Param("firstmonthyear") Date firstmonthyear, @Param("lastmonthyear") Date lastmonthyear);
}
