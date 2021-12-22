package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.models.DTOs.IncomeDayResponseDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.IncomeMonthResponseDTO;
import grupo3.desafioFinalBootcamp.models.HotelBooking;
import grupo3.desafioFinalBootcamp.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceImpTest {
    @Autowired
    ReservationServiceImp service;

    HotelBookingRepository bookingRepo;
    FlightReservationRepository reservationRepo;
    HotelRepository hotelRepo;
    FlightRepository flightRepo;
    TouristicPackageRepository tourpackage;

    @BeforeEach
    public void setUp() {
        service = new ReservationServiceImp(bookingRepo, reservationRepo, hotelRepo, flightRepo, tourpackage);
    }

    @Test
    void addBooking() {
    }

    @Test
    void addReservation() {
    }

    @Test
    void editHotelBooking() {
    }

    @Test
    void editFlightReservation() {
    }

    @Test
    void getHotelBookings() {
    }

    @Test
    void getFlightReservations() {
    }

    @Test
    void deleteHotelBooking() {
    }

    @Test
    void deleteFlightReservation() {
    }

    @Test
    void getIncomeByDay() throws Exception {
        IncomeDayResponseDTO income = service.getIncomeByDay("22/12/2021");
        assertTrue(income.getTotal_income() > 0);
    }

    @Test
    void getIncomeByMonth() throws Exception {
        IncomeMonthResponseDTO income = service.getIncomeByMonth(12, 2021);
        assertTrue(income.getTotal_income() > 0);
    }
}