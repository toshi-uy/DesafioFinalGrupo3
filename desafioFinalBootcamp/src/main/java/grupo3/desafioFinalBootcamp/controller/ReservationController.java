package grupo3.desafioFinalBootcamp.controller;

import grupo3.desafioFinalBootcamp.models.DTOs.FlightReservationDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelBookingDTO;
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

    @PostMapping("/hotel-booking/new")
    public ResponseEntity<StatusDTO> addHotelBooking(@RequestBody HotelBookingDTO booking) {
        return new ResponseEntity<>(service.addBooking(booking), HttpStatus.OK);
    }

    @PostMapping("/flight-reservation/new")
    public ResponseEntity<StatusDTO> addFlightReservation(@RequestBody FlightReservationDTO reservation) {
        return new ResponseEntity<>(service.addReservation(reservation), HttpStatus.OK);
    }


    @DeleteMapping("/hotel-booking/delete")
    public ResponseEntity<StatusDTO> deleteHotelBooking(@RequestParam Integer id) {
        return new ResponseEntity<>(service.deleteHotelBooking(id), HttpStatus.OK);
    }

    @DeleteMapping("/flight-reservation/delete")
    public ResponseEntity<StatusDTO> deleteFlightReservation(@RequestParam Integer id) {
        return new ResponseEntity<>(service.deleteFlightReservation(id), HttpStatus.OK);
    }

    @PutMapping("/hotel-booking/edit")
    public ResponseEntity<StatusDTO> editHotelBooking(@RequestParam Integer id, @RequestBody HotelBookingDTO hotelBookingDTO) {
        return new ResponseEntity<>(service.editHotelBooking(id, hotelBookingDTO), HttpStatus.OK);
    }

    @PutMapping("/flight-reservation/edit")
    public ResponseEntity<StatusDTO> editFlightReservation(@RequestParam Integer id, @RequestBody FlightReservationDTO flightReservationDTO) {
        return new ResponseEntity<>(service.editFlightReservation(id, flightReservationDTO), HttpStatus.OK);
    }

    @GetMapping("/flight-reservations")
    public ResponseEntity<List<FlightReservationDTO>> getFlightReservation() throws Exception {
        return new ResponseEntity<>(service.getFlightReservations(), HttpStatus.OK);
    }

    @GetMapping("/hotel-bookings")
    public ResponseEntity<List<HotelBookingDTO>> getHotelBookings() throws Exception {

        return new ResponseEntity<>(service.getHotelBookings(), HttpStatus.OK);
    }


}
