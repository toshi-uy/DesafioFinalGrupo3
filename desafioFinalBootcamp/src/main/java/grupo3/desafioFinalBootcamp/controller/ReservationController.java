package grupo3.desafioFinalBootcamp.controller;

import grupo3.desafioFinalBootcamp.exceptions.DuplicateBooking;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateReservation;
import grupo3.desafioFinalBootcamp.exceptions.NoBookingFound;
import grupo3.desafioFinalBootcamp.exceptions.NoReservationFound;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightReservationDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelBookingDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.IncomeResponseDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {
    private ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    // ALTAS
    @PostMapping("/hotel-booking/new")
    public ResponseEntity<StatusDTO> addHotelBooking(@RequestBody HotelBookingDTO booking) throws DuplicateBooking {
        return new ResponseEntity<>(service.addBooking(booking), HttpStatus.OK);
    }

    @PostMapping("/flight-reservation/new")
    public ResponseEntity<StatusDTO> addFlightReservation(@RequestBody FlightReservationDTO reservation) throws DuplicateReservation {
        return new ResponseEntity<>(service.addReservation(reservation), HttpStatus.OK);
    }

    // BAJAS
    @DeleteMapping("/hotel-booking/delete")
    public ResponseEntity<StatusDTO> deleteHotelBooking(@RequestParam Integer id) {
        return new ResponseEntity<>(service.deleteHotelBooking(id), HttpStatus.OK);
    }

    @DeleteMapping("/flight-reservation/delete")
    public ResponseEntity<StatusDTO> deleteFlightReservation(@RequestParam Integer id) {
        return new ResponseEntity<>(service.deleteFlightReservation(id), HttpStatus.OK);
    }

    // MODIFICACIONES
    @PutMapping("/hotel-booking/edit")
    public ResponseEntity<StatusDTO> editHotelBooking(@RequestParam Integer id, @RequestBody HotelBookingDTO hotelBookingDTO) throws NoBookingFound {
        return new ResponseEntity<>(service.editHotelBooking(id, hotelBookingDTO), HttpStatus.OK);
    }

    @PutMapping("/flight-reservation/edit")
    public ResponseEntity<StatusDTO> editFlightReservation(@RequestParam Integer id, @RequestBody FlightReservationDTO flightReservationDTO) throws NoReservationFound {
        return new ResponseEntity<>(service.editFlightReservation(id, flightReservationDTO), HttpStatus.OK);
    }

    // CONSULTAS/LECTURAS
    @GetMapping("/flight-reservations")
    public ResponseEntity<List<FlightReservationDTO>> getFlightReservation() throws Exception {
        return new ResponseEntity<>(service.getFlightReservations(), HttpStatus.OK);
    }

    @GetMapping("/hotel-bookings")
    public ResponseEntity<List<HotelBookingDTO>> getHotelBookings() throws Exception {

        return new ResponseEntity<>(service.getHotelBookings(), HttpStatus.OK);
    }

    @GetMapping("/income")
    public ResponseEntity<IncomeResponseDTO> getIncome(@RequestParam(required = false) String date, @RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year) throws Exception {

        if (date == null && month == null && year == null)
            throw new Exception("Please enter the parameters for this request");
        else if (date != null && month != null && year != null)
            throw new Exception("Please enter the date or the month and year");
        else if (date != null && (month != null || year != null))
            throw new Exception("Please enter the date or the month and year");
        else if (date != null && month == null && year == null)
            return new ResponseEntity<>(service.getIncomeByDay(date), HttpStatus.OK);
        else
            return new ResponseEntity<>(service.getIncomeByMonth(month, year), HttpStatus.OK);
    }


}
