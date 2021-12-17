package grupo3.desafioFinalBootcamp.repositories;

import grupo3.desafioFinalBootcamp.models.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {

}
