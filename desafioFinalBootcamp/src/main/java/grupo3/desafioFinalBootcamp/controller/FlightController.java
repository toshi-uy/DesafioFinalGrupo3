package grupo3.desafioFinalBootcamp.controller;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // ALTAS
    @PostMapping("/new")
    public ResponseEntity<StatusDTO> addFlight(@RequestBody FlightDTO flight) throws DuplicateFlightId, DuplicateFlightNumber {
        return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.OK);
    }

    // BAJAS
    @DeleteMapping("/delete")
    public ResponseEntity<StatusDTO> deleteFlight(@RequestParam String flightNumber) throws NoFlightFound, UnableToDeleteFlight {
        return new ResponseEntity<>(flightService.deleteFlightByFlightNumber(flightNumber), HttpStatus.OK);
    }

    // CONSULTAS/LECTURAS
    @GetMapping
    public ResponseEntity<List<FlightDTO>> getHotels(@RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo, @RequestParam(required = false) String origin, @RequestParam(required = false) String destination) throws Exception {

        if (dateFrom == null || dateTo == null || origin == null || destination == null)
            if (dateFrom != null || dateTo != null || destination != null)
                throw new MissingParameters();
            else
                return new ResponseEntity<>(flightService.getFlights(), HttpStatus.OK);
        return new ResponseEntity<>(flightService.getListedFlights(dateFrom, dateTo, origin, destination), HttpStatus.OK);
    }

    // MODIFICACIONES
    @PutMapping("/edit")
    public ResponseEntity<StatusDTO> editHotelByCode(@RequestParam String flightCode, @RequestBody FlightDTO flightDTO) throws NoFlightFound {
        return new ResponseEntity<>(flightService.editFlightByCode(flightCode, flightDTO), HttpStatus.OK);
    }
}
