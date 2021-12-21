package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.DTOs.*;
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
    private final FlightRepository flightRepo;
    private final HotelRepository hotelRepo;
    ModelMapper mapper = new ModelMapper();

    public ReservationServiceImp(HotelBookingRepository bookingRepo, FlightReservationRepository reservationRepo, FlightRepository flightRepo, HotelRepository hotelRepo) {
        this.bookingRepo = bookingRepo;
        this.reservationRepo = reservationRepo;
        this.flightRepo = flightRepo;
        this.hotelRepo = hotelRepo;
    }

    // ALTAS
    public StatusDTO addBooking(HotelBookingDTO booking) throws DuplicateBooking {
        List<HotelBooking> reservas = bookingRepo.findAll();
        for (HotelBooking hb : reservas) {
            if (hb.getUsername().equals(booking.getUsername()) &&
                    hb.getDateFrom().equals(booking.getDateFrom()) && hb.getDateTo().equals(booking.getDateTo()) &&
                    hb.getDestination().equals(booking.getDestination()) && hb.getHotelCode().equals(booking.getHotelCode()) &&
                    hb.getPeopleAmount() == booking.getPeopleAmount() && hb.getRoomType().equals(booking.getRoomType()) &&
                    hb.getPeopleHotel().equals(booking.getPeopleHotel()) && hb.getPaymentmethod().equals(booking.getPaymentmethod()))
                throw new DuplicateBooking();
        }
        HotelBooking nuevo = mapper.map(booking, HotelBooking.class);
        nuevo.setBookingDate(new Date());
        Hotel hotel = hotelRepo.findByHotelCode(booking.getHotelCode());
        nuevo.setPrice(hotel.getRoomPrice());
        bookingRepo.save(nuevo);
        return new StatusDTO("Reserva de hotel creada con éxito.");
    }

    public StatusDTO addReservation(FlightReservationDTO reservation) throws DuplicateReservation {
        List<FlightReservation> reservas = reservationRepo.findAll();
        for (FlightReservation fr : reservas) {
            if (fr.getFlightNumber().equals(reservation.getFlightNumber()) &&
                    fr.getUserName().equals(reservation.getUsername()) &&
                    fr.getGoingDate().equals(reservation.getGoingDate()) &&
                    fr.getReturnDate().equals(reservation.getReturnDate()) &&
                    fr.getOrigin().equals(reservation.getOrigin()) && fr.getDestination().equals(reservation.getDestination()) &&
                    fr.getSeats() == reservation.getSeats() && fr.getSeatType().equals(reservation.getSeatType()) &&
                    fr.getPeopleFlight().equals(reservation.getPeopleFlight()) &&
                    fr.getPaymentMethod().equals(reservation.getPaymentMethod()))
                throw new DuplicateReservation();
        }
        FlightReservation nuevo = mapper.map(reservation, FlightReservation.class);
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
        bookingRepo.deleteById(id);
        return new StatusDTO("Reserva de hotel dada de baja correctamente");
    }

    public StatusDTO deleteFlightReservation(Integer id) {
        reservationRepo.deleteById(id);
        return new StatusDTO("Reserva de vuelo dada de baja correctamente");
    }

    //CAJA
    public IncomeResponseDTO getIncomeByDay(String paramdate) throws Exception {

        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(paramdate);
        Date newDate = new Date(date.getTime() - 3 * (3600 * 1000));

        Double income = reservationRepo.getHotelFlightReservationsSumPerDay(newDate) ;
        income += bookingRepo.getHotelBookingSumPerDay(newDate);

        IncomeResponseDTO incomeResponse = new IncomeResponseDTO(date, income);
        return incomeResponse;

    }

    public IncomeResponseDTO getIncomeByMonth(int month, int year) throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstmonth = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastmonth = cal.getTime();
        Date firstyear = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/" + year);
        Date lastyear = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/" + year);

        double amount = reservationRepo.getReservationSumPerMonth(firstmonth, lastmonth, firstyear, lastyear);
        amount += bookingRepo.getBookingSumPerMonth(firstmonth, lastmonth, firstyear, lastyear);
        IncomeResponseDTO incomeResponse = new IncomeResponseDTO(month,year,amount);
        incomeResponse.setMonth(month);
        incomeResponse.setYear(year);
        incomeResponse.setTotal_income(amount);
        return incomeResponse;

    }
}
