package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.DuplicateBooking;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateReservation;
import grupo3.desafioFinalBootcamp.exceptions.NoBookingFound;
import grupo3.desafioFinalBootcamp.exceptions.NoReservationFound;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightReservationDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelBookingDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.IncomeResponseDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;

import java.util.List;

public interface ReservationService {
    StatusDTO addBooking(HotelBookingDTO booking) throws DuplicateBooking;

    StatusDTO addReservation(FlightReservationDTO flight) throws DuplicateReservation;

    StatusDTO deleteHotelBooking(Integer id);

    StatusDTO deleteFlightReservation(Integer id);

    StatusDTO editHotelBooking(Integer id, HotelBookingDTO hotelBookingDTO) throws NoBookingFound;

    StatusDTO editFlightReservation(Integer id, FlightReservationDTO flightReservationDTO) throws NoReservationFound;

    List<HotelBookingDTO> getHotelBookings() throws Exception;

    List<FlightReservationDTO> getFlightReservations() throws Exception;

    IncomeResponseDTO getIncomeByDay(String paramdate) throws Exception;

     IncomeResponseDTO getIncomeByMonth(int month, int year) throws Exception;




    }
