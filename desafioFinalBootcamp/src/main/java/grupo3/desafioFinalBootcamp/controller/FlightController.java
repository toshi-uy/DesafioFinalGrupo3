package grupo3.desafioFinalBootcamp.controller;

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

    @PostMapping("/new")
    public ResponseEntity<StatusDTO> addFlight(@RequestBody FlightDTO flight) {
        return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<StatusDTO> deleteFlight(@RequestParam String flightNumber) {
        return new ResponseEntity<>(flightService.deleteFlightByFlightNumber(flightNumber), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightDTO>> getHotels(@RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo, @RequestParam(required = false) String origin, @RequestParam(required = false) String destination) throws Exception {

        if (dateFrom == null || dateTo == null || origin == null || destination == null)
            if (dateFrom != null || dateTo != null || destination != null)
                throw new Exception("Please enter the 4 requested parameter: Date From, Date To, Origin and Destination");
            else
                return new ResponseEntity<>(flightService.getFlights(), HttpStatus.OK);
        return new ResponseEntity<>(flightService.getListedFlights(dateFrom, dateTo, origin, destination), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<StatusDTO> editHotelByCode(@RequestParam String hotelCode, @RequestBody FlightDTO flightDTO) {
        return new ResponseEntity<>(flightService.editFlightByCode(hotelCode, flightDTO), HttpStatus.OK);
    }
}
