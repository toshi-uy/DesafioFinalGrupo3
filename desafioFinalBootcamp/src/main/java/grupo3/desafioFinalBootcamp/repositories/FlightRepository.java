package grupo3.desafioFinalBootcamp.repositories;

import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("FROM Flight f")
    List<Flight> getAll();

    @Query("FROM Flight f WHERE f.id = :id")
    Flight findById(@Param("id") int id);

    @Query("DELETE FROM Flight f WHERE f.flightNumber = :flightNumber")
    StatusDTO deleteFlightByFlightNumber(@Param("flightNumber") String flightNumber);

    @Query("SELECT f FROM Flight f WHERE f.goingDate >= :datefrom AND f.returnDate <= :dateto AND f.origin = :origin AND f.destination = :destination ")
    List<Flight> findListedFlights(@Param("datefrom") Date datefrom, @Param("dateto") Date dateto, @Param("origin") String origin, @Param("destination") String destination);

    @Query("SELECT f FROM Flight f WHERE f.flightNumber LIKE :flightNumber")
    Flight findByFlightNumber(@Param("flightNumber") String flighNumber);
}
