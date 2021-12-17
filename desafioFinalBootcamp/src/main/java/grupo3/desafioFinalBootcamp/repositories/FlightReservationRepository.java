package grupo3.desafioFinalBootcamp.repositories;

import grupo3.desafioFinalBootcamp.models.FlightReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Integer> {
    
}
