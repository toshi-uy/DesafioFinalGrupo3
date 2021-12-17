package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.models.DTOs.FlightDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;

import java.util.List;

public interface FlightService {
    StatusDTO addFlight(FlightDTO flight);

    StatusDTO deleteFlightByFlightNumber(String flightNumber);

    StatusDTO editFlightByCode(String flightNumber);

    List<FlightDTO> getListedFlights(String dateFrom, String dateTo, String origin, String destination);

    List<FlightDTO> getFlights();

}
