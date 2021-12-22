package grupo3.desafioFinalBootcamp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateFlightId;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateFlightNumber;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateHotelCode;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateHotelId;
import grupo3.desafioFinalBootcamp.models.BookResId;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightReservationDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelBookingDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.PackageDTO;
import grupo3.desafioFinalBootcamp.models.Flight;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.models.Package;
import grupo3.desafioFinalBootcamp.repositories.*;
import grupo3.desafioFinalBootcamp.services.FlightServiceImp;
import grupo3.desafioFinalBootcamp.services.HotelServiceImp;
import grupo3.desafioFinalBootcamp.services.ReservationServiceImp;
import grupo3.desafioFinalBootcamp.services.TouristicPackage;
import grupo3.desafioFinalBootcamp.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TouristicPackageControllerTest {

    @Autowired
    MockMvc mockMVc;

    @Autowired
    HotelServiceImp hotelService;
    @Autowired
    FlightServiceImp flightService;
    @Autowired
    HotelRepository hotelRepo;
    @Autowired
    FlightRepository flightRepo;
    @Autowired
    ReservationServiceImp reservationService;
    @Autowired
    FlightReservationRepository flightReservationRepo;
    @Autowired
    HotelBookingRepository hotelBookingRepo;
    @Autowired
    TouristicPackageRepository repo;

    @BeforeEach
    void setUp() throws Exception {
        hotelService.addHotel(Utils.getHotelDTO());
        flightService.addFlight(Utils.getFlightDTO());
        reservationService.addBooking(Utils.getHotelBookingDTO());
        reservationService.addReservation(Utils.getFlightReservationDTO());
    }

    @AfterEach
    void tearDown() {
        Flight flight = flightRepo.findByFlightNumber("ABC123");
        if (flight != null)
            flightRepo.deleteById(flight.getId());
        Hotel hotel = hotelRepo.findByHotelCode("ABC123");
        if (hotel != null)
            hotelRepo.deleteById(hotel.getId());
    }

    @Test
    void newPackage() throws Exception {
        PackageDTO packageDTO = Utils.getPackageDTO();
        Integer id1 = hotelRepo.findByHotelCode("ABC123").getId();
        Integer id2 = flightRepo.findByFlightNumber("ABC123").getId();
        BookResId bookResId = new BookResId();
        bookResId.setBookResId1(id1);
        bookResId.setBookResId2(id2);
        packageDTO.setBookingsOrReservations(bookResId);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(packageDTO);

        mockMVc.perform(MockMvcRequestBuilders.post("/touristicpackage/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Paquete Turístico dado de alta correctamente"));

    }

    @Test
    void editPackage() {
    }

    @Test
    void getAllPackages() {
    }

    @Test
    void deletePackage() {
    }
}