package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.models.DTOs.FlightReservationDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelBookingDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.FlightReservation;
import grupo3.desafioFinalBootcamp.models.HotelBooking;
import grupo3.desafioFinalBootcamp.repositories.FlightReservationRepository;
import grupo3.desafioFinalBootcamp.repositories.HotelBookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImp implements ReservationService {

    ModelMapper mapper = new ModelMapper();
    private HotelBookingRepository bookingRepo;
    private FlightReservationRepository reservationRepo;

    public ReservationServiceImp(HotelBookingRepository bookingRepo, FlightReservationRepository reservationRepo) {
        this.bookingRepo = bookingRepo;
        this.reservationRepo = reservationRepo;
    }

    // ALTAS
    public StatusDTO addBooking(HotelBookingDTO booking) {
        HotelBooking nuevo = mapper.map(booking, HotelBooking.class);
        bookingRepo.save(nuevo);
        return new StatusDTO("Reserva de hotel creada con éxito.");
    }

    public StatusDTO addReservation(FlightReservationDTO reservation) {
        FlightReservation nuevo = mapper.map(reservation, FlightReservation.class);
        reservationRepo.save(nuevo);
        return new StatusDTO("Reserva de vuelo creada con éxito.");
    }

    // MODIFICACIONES
    public StatusDTO editHotelBooking(Integer id) {
        bookingRepo.save(bookingRepo.findById(id).get());
        return new StatusDTO("Reserva de hotel modificada correctamente");
    }

    public StatusDTO editFlightReservation(Integer id) {
        reservationRepo.save(reservationRepo.findById(id).get());
        return new StatusDTO("Reserva de vuelo modificada correctamente");
    }


    // CONSULTAS/LECTURAS
    public List<HotelBookingDTO> getHotelBookings() {
        List<HotelBooking> hotelBookingList = bookingRepo.findAll();
        return hotelBookingList.stream().map(hotelBookingDTO -> mapper.map(hotelBookingDTO, HotelBookingDTO.class)).collect(Collectors.toList());

    }

    public List<FlightReservationDTO> getFlightReservations() {
        List<FlightReservation> flightReservationList = reservationRepo.findAll();
        return flightReservationList.stream().map(flightReservationDTO -> mapper.map(flightReservationDTO, FlightReservationDTO.class)).collect(Collectors.toList());

    }

    // BAJAS
    public StatusDTO deleteHotelBooking(Integer id) {
        bookingRepo.deleteById(id);
        return new StatusDTO("Reserva de hotel dada de baja correctamente");
    }

    public StatusDTO deleteFlightReservation(Integer id) {
        reservationRepo.deleteById(id);
        return new StatusDTO("Reserva de vuelo dada de baja correctamente");
    }
}
