package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.models.DTOs.FlightReservationDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelBookingDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;

import java.util.List;

public interface ReservationService {
    StatusDTO addBooking(HotelBookingDTO booking);

    StatusDTO addReservation(FlightReservationDTO flight);

    StatusDTO deleteHotelBooking(Integer id);

    StatusDTO deleteFlightReservation(Integer id);

    StatusDTO editHotelBooking(Integer id);

    StatusDTO editFlightReservation(Integer id);

    List<HotelBookingDTO> getHotelBookings();

    List<FlightReservationDTO> getFlightReservations();


}
