package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightReservationDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelBookingDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.IncomeResponseDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Flight;
import grupo3.desafioFinalBootcamp.models.FlightReservation;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.models.HotelBooking;
import grupo3.desafioFinalBootcamp.repositories.FlightRepository;
import grupo3.desafioFinalBootcamp.repositories.FlightReservationRepository;
import grupo3.desafioFinalBootcamp.repositories.HotelBookingRepository;
import grupo3.desafioFinalBootcamp.repositories.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImp implements ReservationService {

    private final HotelBookingRepository bookingRepo;
    private final FlightReservationRepository reservationRepo;
    private final HotelRepository hotelRepo;
    private final FlightRepository flightRepo;
    ModelMapper mapper = new ModelMapper();

    public ReservationServiceImp(HotelBookingRepository bookingRepo, FlightReservationRepository reservationRepo, HotelRepository hotelRepo, FlightRepository flightRepo) {
        this.bookingRepo = bookingRepo;
        this.reservationRepo = reservationRepo;
        this.hotelRepo = hotelRepo;
        this.flightRepo = flightRepo;
    ModelMapper mapper = new ModelMapper();
    }

    // ALTAS
    public StatusDTO addBooking(HotelBookingDTO booking) throws DuplicateBooking, NoHotelFound {
        List<HotelBooking> reservas = bookingRepo.findAll();
        Hotel hotel = hotelRepo.findByHotelCode(booking.getHotelCode());
        if (hotel == null) {
            throw new NoHotelFound();
        }
        for (HotelBooking hb : reservas) {
            if (hb.getUsername().equals(booking.getUsername()) &&
                    hb.getDateFrom().compareTo(booking.getDateFrom()) == 0 &&
                    hb.getDateTo().compareTo(booking.getDateTo()) == 0 &&
                    hb.getDestination().equals(booking.getDestination()) &&
                    hb.getHotelCode().equals(booking.getHotelCode()) &&
                    hb.getPeopleAmount().equals(booking.getPeopleAmount()) &&
                    hb.getRoomType().equals(booking.getRoomType()))
                throw new DuplicateBooking();
        }
        mapper.getConfiguration().setAmbiguityIgnored(true);
        HotelBooking nuevo = mapper.map(booking, HotelBooking.class);
        nuevo.setHotel(hotel);
        nuevo.setBookingDate(new Date());
        Hotel hotel1 = hotelRepo.findByHotelCode(booking.getHotelCode());
        nuevo.setPrice(hotel1.getRoomPrice());
        bookingRepo.save(nuevo);
        return new StatusDTO("Reserva de hotel creada con éxito.");
    }

    public StatusDTO addReservation(FlightReservationDTO reservation) throws DuplicateReservation, NoFlightFound {
        List<FlightReservation> reservas = reservationRepo.findAll();
        Flight flight = flightRepo.findByFlightNumber(reservation.getFlightNumber());
        if (flight == null) {
            throw new NoFlightFound();
        }
        for (FlightReservation fr : reservas) {
            if (fr.getFlightNumber().equals(reservation.getFlightNumber()) &&
                fr.getUserName().equals(reservation.getUsername()) &&
                fr.getGoingDate().compareTo(reservation.getGoingDate()) == 0 &&
                fr.getReturnDate().compareTo(reservation.getReturnDate()) == 0 &&
                fr.getOrigin().equals(reservation.getOrigin()) && fr.getDestination().equals(reservation.getDestination()) &&
                fr.getSeats().equals(reservation.getSeats()) && fr.getSeatType().equals(reservation.getSeatType()))
                throw new DuplicateReservation();
        }
        FlightReservation nuevo = mapper.map(reservation, FlightReservation.class);
        nuevo.setFlight(flight);
        nuevo.setBookingDate(new Date());
        Flight vuelo = flightRepo.findByFlightNumber(reservation.getFlightNumber());
        nuevo.setPrice(vuelo.getFlightPrice() * reservation.getSeats());
        reservationRepo.save(nuevo);
        return new StatusDTO("Reserva de vuelo creada con éxito.");
    }

    // MODIFICACIONES
    public StatusDTO editHotelBooking(Integer id, HotelBookingDTO hotelBookingDTO) throws NoBookingFound {
        HotelBooking check = bookingRepo.getById(id);
        if (check == null) {
            throw new NoBookingFound();
        }
        HotelBooking modified = mapper.map(hotelBookingDTO, HotelBooking.class);
        modified.setId(id);
        bookingRepo.save(modified);
        return new StatusDTO("Reserva de hotel modificada correctamente");
    }

    public StatusDTO editFlightReservation(Integer id, FlightReservationDTO flightReservationDTO) throws NoReservationFound {
        FlightReservation check = reservationRepo.getById(id);
        if (check == null) {
            throw new NoReservationFound();
        }
        FlightReservation modified = mapper.map(flightReservationDTO, FlightReservation.class);
        modified.setId(id);
        reservationRepo.save(modified);
        return new StatusDTO("Reserva de vuelo modificada correctamente");
    }


    // CONSULTAS/LECTURAS
    public List<HotelBookingDTO> getHotelBookings() throws Exception {
        List<HotelBooking> hotelBookingList = bookingRepo.findAll();
        List<HotelBookingDTO> hotelBookingDTOList = hotelBookingList.stream().map(hotelBookingDTO -> mapper.map(hotelBookingDTO, HotelBookingDTO.class)).collect(Collectors.toList());
        if (hotelBookingDTOList.size() == 0)
            throw new NoHotelData();

        return hotelBookingDTOList;
    }

    public List<FlightReservationDTO> getFlightReservations() throws Exception {

        List<FlightReservation> flightReservationList = reservationRepo.findAll();
        List<FlightReservationDTO> flightReservationDTOList = flightReservationList.stream().map(flightReservationDTO -> mapper.map(flightReservationDTO, FlightReservationDTO.class)).collect(Collectors.toList());
        if (flightReservationDTOList.size() == 0)
            throw new NoFlightFound();
        return flightReservationDTOList;
    }

    // BAJAS
    public StatusDTO deleteHotelBooking(Integer id) {
        //verificar que no se encuentre en un paquete
        bookingRepo.deleteById(id);
        return new StatusDTO("Reserva de hotel dada de baja correctamente");
    }

    public StatusDTO deleteFlightReservation(Integer id) {
        //verificar que no se encuentre en un paquete
        reservationRepo.deleteById(id);
        return new StatusDTO("Reserva de vuelo dada de baja correctamente");
    }

    //CAJA
    public IncomeResponseDTO getIncomeByDay(String paramdate) throws Exception {

        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(paramdate);

        List<FlightReservation> flightReservationList = reservationRepo.findAll();
        List<HotelBooking> hotelBookingList = bookingRepo.findAll();

        double income = 0.0;
        for (FlightReservation reservation : flightReservationList) {
            reservation.setBookingDate(new Date(reservation.getBookingDate().getTime() + 3 * (3600 * 1000)));
            if (reservation.getBookingDate().equals(date))
                income += reservation.getPrice();
        }

        for (HotelBooking booking : hotelBookingList) {
            booking.setBookingDate(new Date(booking.getBookingDate().getTime() + 3 * (3600 * 1000)));
            if (booking.getBookingDate().equals(date))
                income += booking.getPrice();
        }

//        income += reservationRepo.getHotelFlightReservationsSumPerDay(date);
//        income += bookingRepo.getHotelBookingSumPerDay(date);

        IncomeResponseDTO incomeResponse = new IncomeResponseDTO(date, income);
        return incomeResponse;

    }

    public IncomeResponseDTO getIncomeByMonth(int month, int year) throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.YEAR, year);
        Date firstmonth = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastmonth = cal.getTime();
        double amount = 0;

        amount = reservationRepo.getReservationSumPerMonth(firstmonth, lastmonth);
        amount += bookingRepo.getBookingSumPerMonth(firstmonth, lastmonth);
        IncomeResponseDTO incomeResponse = new IncomeResponseDTO(month, year, amount);
        incomeResponse.setMonth(month);
        incomeResponse.setYear(year);
        incomeResponse.setTotal_income(amount);
        return incomeResponse;

    }
}
