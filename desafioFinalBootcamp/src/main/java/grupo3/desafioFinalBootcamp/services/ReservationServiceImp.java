package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.*;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightReservationDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelBookingDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.IncomeResponseDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
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
    }


    private boolean hotelAvailability(Hotel hotel, Date desde, Date hasta) {
        if (hotel != null && desde != null & hasta != null) {
            return hotel.getIsBooking().equals(false) && hotel.getDisponibilityDateFrom().compareTo(desde) <= 0 && hotel.getDisponibilityDateTo().compareTo(hasta) >= 0;
        }
        return false;
    }

    private boolean hotelDestinationValidation(String destino) {
        if (destino != null) {
            for (Hotel h : hotelRepo.getAll()) {
                if (h.getPlace().equals(destino))
                    return true;
            }
        }
        return false;
    }

    private boolean roomTypeValidation(int cantPersonas, String tipoHabitacion) {
        if (tipoHabitacion != null) {
            if (cantPersonas == 1 && tipoHabitacion.equals("Single"))
                return true;
            if (cantPersonas == 2 && tipoHabitacion.equals("Double"))
                return true;
            if (cantPersonas == 3 && tipoHabitacion.equals("Triple"))
                return true;
            return cantPersonas > 3 && tipoHabitacion.equals("Múltiple");
        }
        return false;
    }

    // ALTAS
    public StatusDTO addBooking(HotelBookingDTO booking) throws DuplicateBooking, NoHotelFound, Exception {
        if (booking != null && booking.getBooking().getPeopleAmount() > 0) {
            if (validarFechas(booking.getBooking().getDateFrom(), booking.getBooking().getDateTo())) {
                if (hotelDestinationValidation(booking.getBooking().getDestination())) {
                    if (roomTypeValidation(booking.getBooking().getPeopleAmount(), booking.getBooking().getRoomType())) {
                        Hotel hotel = hotelRepo.findByHotelCode(booking.getBooking().getHotelCode());
                        if (hotel == null) {
                            throw new NoHotelFound();
                        }

                        List<HotelBooking> reservas = bookingRepo.findAll();
                        for (HotelBooking hb : reservas) {
                            if (hb.getUsername().equals(booking.getUsername()) &&
                                    hb.getBooking().getDateFrom().compareTo(booking.getBooking().getDateFrom()) == 0 &&
                                    hb.getBooking().getDateTo().compareTo(booking.getBooking().getDateTo()) == 0 &&
                                    hb.getBooking().getDestination().equals(booking.getBooking().getDestination()) &&
                                    hb.getBooking().getHotelCode().equals(booking.getBooking().getHotelCode()) &&
                                    hb.getBooking().getPeopleAmount().equals(booking.getBooking().getPeopleAmount()) &&
                                    hb.getBooking().getRoomType().equals(booking.getBooking().getRoomType()))
                                throw new DuplicateBooking();
                        }
                        mapper.getConfiguration().setAmbiguityIgnored(true);
                        HotelBooking nuevo = mapper.map(booking, HotelBooking.class);
                        nuevo.setBookingDate(new Date());
                        Hotel hotel1 = hotelRepo.findByHotelCode(booking.getBooking().getHotelCode());
                        nuevo.setPrice(hotel1.getRoomPrice());
                        nuevo.setId(booking.getBooking().getBookingId());
                        bookingRepo.save(nuevo);
                        return new StatusDTO("Reserva de hotel creada con éxito.");
                    }
                    throw new Exception();
                }
                throw new Exception();
            }
            throw new Exception();
        }
        throw new Exception();
    }

    private boolean flightPlacesValidation(String origen, String destino) {
        if (destino != null && origen != null) {
            for (Flight f : flightRepo.getAll()) {
                if (f.getOrigin().equals(origen) && f.getDestination().equals(destino))
                    return true;
            }
        }
        return false;
    }

    private boolean validarFechas(Date desde, Date hasta) {
        if (desde == null || hasta == null)
            return false;
        return desde.compareTo(hasta) < 0;
    }

    private boolean FlightAvailability(Flight vuelo, Date desde, Date hasta) {
        if (vuelo != null && desde != null & hasta != null) {
            return vuelo.getGoingDate().compareTo(desde) >= 0 && vuelo.getGoingDate().compareTo(hasta) <= 0 || vuelo.getReturnDate().compareTo(hasta) <= 0 && vuelo.getReturnDate().compareTo(desde) >= 0;
        }
        return false;
    }

    public StatusDTO addReservation(FlightReservationDTO reservation) throws DuplicateReservation, NoFlightFound, Exception {
        if (reservation != null && reservation.getFlightReservation().getSeats() > 0) {
            if (validarFechas(reservation.getFlightReservation().getGoingDate(), reservation.getFlightReservation().getReturnDate())) {
                if (flightPlacesValidation(reservation.getFlightReservation().getOrigin(), reservation.getFlightReservation().getDestination())) {
                    Flight flight = flightRepo.findByFlightNumber(reservation.getFlightReservation().getFlightNumber());
                    if (flight == null) {
                        throw new NoFlightFound();
                    }
                    List<FlightReservation> reservas = reservationRepo.findAll();

                    for (FlightReservation fr : reservas) {
                        if (fr.getFlightReservation().getFlightNumber().equals(reservation.getFlightReservation().getFlightNumber()) &&
                                fr.getUserName().equals(reservation.getUserName()) &&
                                fr.getFlightReservation().getGoingDate().compareTo(reservation.getFlightReservation().getGoingDate()) == 0 &&
                                fr.getFlightReservation().getReturnDate().compareTo(reservation.getFlightReservation().getReturnDate()) == 0 &&
                                fr.getFlightReservation().getOrigin().equals(reservation.getFlightReservation().getOrigin()) && fr.getFlightReservation().getDestination().equals(reservation.getFlightReservation().getDestination()) &&
                                fr.getFlightReservation().getSeats().equals(reservation.getFlightReservation().getSeats()) && fr.getFlightReservation().getSeatType().equals(reservation.getFlightReservation().getSeatType()))
                            throw new DuplicateReservation();
                    }
                    mapper.getConfiguration().setAmbiguityIgnored(true);
                    FlightReservation nuevo = mapper.map(reservation, FlightReservation.class);
                    nuevo.setBookingDate(new Date());
                    Flight vuelo = flightRepo.findByFlightNumber(reservation.getFlightReservation().getFlightNumber());
                    nuevo.setPrice(vuelo.getFlightPrice() * reservation.getFlightReservation().getSeats());
                    nuevo.setId(reservation.getFlightReservation().getReservationId());
                    reservationRepo.save(nuevo);
                    return new StatusDTO("Reserva de vuelo creada con éxito.");
                }
                throw new Exception();
            }
            throw new Exception();
        }
        throw new Exception();
    }

    // MODIFICACIONES
    public StatusDTO editHotelBooking(Integer id, HotelBookingDTO hotelBookingDTO) throws NoBookingFound {
        HotelBooking check = bookingRepo.getById(id);
        if (check == null) {
            throw new NoBookingFound();
        }
        mapper.getConfiguration().setAmbiguityIgnored(true);
        check = mapper.map(hotelBookingDTO, HotelBooking.class);
//        modified.setBookingDate(check.getBookingDate());
//        modified.setPrice(check.getPrice());
//        modified.setId(id);
        bookingRepo.save(check);
        return new StatusDTO("Reserva de hotel modificada correctamente");
    }

    public StatusDTO editFlightReservation(Integer id, FlightReservationDTO flightReservationDTO) throws NoReservationFound {
        FlightReservation check = reservationRepo.getById(id);
        if (check == null) {
            throw new NoReservationFound();
        }
        mapper.getConfiguration().setAmbiguityIgnored(true);
        FlightReservation modified = mapper.map(flightReservationDTO, FlightReservation.class);
        modified.setBookingDate(check.getBookingDate());
        modified.setPrice(check.getPrice());
        modified.setId(id);
        reservationRepo.save(modified);
        return new StatusDTO("Reserva de vuelo modificada correctamente");
    }


    // CONSULTAS/LECTURAS
    public List<HotelBookingDTO> getHotelBookings() throws Exception {
        List<HotelBooking> hotelBookingList = bookingRepo.findAll();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        List<HotelBookingDTO> hotelBookingDTOList = hotelBookingList.stream().map(hotelBookingDTO -> mapper.map(hotelBookingDTO, HotelBookingDTO.class)).collect(Collectors.toList());
        if (hotelBookingDTOList.size() == 0)
            throw new NoHotelData();

        return hotelBookingDTOList;
    }

    public List<FlightReservationDTO> getFlightReservations() throws Exception {

        List<FlightReservation> flightReservationList = reservationRepo.findAll();
        mapper.getConfiguration().setAmbiguityIgnored(true);
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
