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

    StatusDTO editHotelBooking(Integer id, HotelBookingDTO hotelBookingDTO);

    StatusDTO editFlightReservation(Integer id, FlightReservationDTO flightReservationDTO);

    List<HotelBookingDTO> getHotelBookings() throws Exception;

    List<FlightReservationDTO> getFlightReservations() throws Exception;


}
