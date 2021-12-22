package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.DTOs.*;

import java.util.List;

public interface ReservationService {
    StatusDTO addBooking(HotelBookingDTO booking) throws DuplicateBooking, NoHotelFound, Exception;

    StatusDTO addReservation(FlightReservationDTO flight) throws DuplicateReservation, NoFlightFound, Exception;

    StatusDTO deleteHotelBooking(Integer id);

    StatusDTO deleteFlightReservation(Integer id);

    StatusDTO editHotelBooking(Integer id, HotelBookingDTO hotelBookingDTO) throws NoBookingFound;

    StatusDTO editFlightReservation(Integer id, FlightReservationDTO flightReservationDTO) throws NoReservationFound;

    List<HotelBookingDTO> getHotelBookings() throws Exception;

    List<FlightReservationDTO> getFlightReservations() throws Exception;

    IncomeDayResponseDTO getIncomeByDay(String paramdate) throws Exception;

    IncomeMonthResponseDTO getIncomeByMonth(int month, int year) throws Exception;




    }
