package grupo3.desafioFinalBootcamp.repositories;

import grupo3.desafioFinalBootcamp.models.Flight;
import grupo3.desafioFinalBootcamp.models.FlightReservation;
import grupo3.desafioFinalBootcamp.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Integer> {

    @Query("SELECT sum(FB.price) FROM FlightReservation FB WHERE FB.bookingDate = '2021-10-15 00:00:00'")
    Double getHotelFlightReservationsSumPerDay(@Param("day") Date day);

    @Query("SELECT sum(FB.price) FROM FlightReservation FB WHERE FB.bookingDate BETWEEN :firstmonthyear AND :lastmonthyear")
    Double getReservationSumPerMonth(@Param("firstmonthyear") Date firstmonthyear, @Param("lastmonthyear") Date lastmonthyear);
}
