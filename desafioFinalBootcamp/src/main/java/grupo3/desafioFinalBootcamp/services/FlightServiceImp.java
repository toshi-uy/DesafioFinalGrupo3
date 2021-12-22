package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Flight;
import grupo3.desafioFinalBootcamp.models.FlightReservation;
import grupo3.desafioFinalBootcamp.repositories.FlightRepository;
import grupo3.desafioFinalBootcamp.repositories.FlightReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImp implements FlightService {
    final FlightRepository repo;
    final FlightReservationRepository bookingsRepo;
    ModelMapper mapper = new ModelMapper();

    public FlightServiceImp(FlightRepository repo, FlightReservationRepository bookingsRepo) {
        this.repo = repo;
        this.bookingsRepo = bookingsRepo;
    }

    // ALTAS
    @Override
    public StatusDTO addFlight(FlightDTO flight) throws DuplicateFlightNumber, DuplicateFlightId {
        Flight nuevo = mapper.map(flight, Flight.class);
        if (repo.findAll().size() == 0) {
            repo.save(nuevo);
            return new StatusDTO("Vuelo creado con éxito.");
        }
        Flight check = repo.findByFlightNumber(flight.getFlightNumber());
        if (check != null) {
            throw new DuplicateFlightNumber();
        }
        check = repo.findFlightById(flight.getId());
        if (check != null) {
            throw new DuplicateFlightId();
        }
        repo.save(nuevo);
        return new StatusDTO("Vuelo creado con éxito.");
    }

    // MODIFICACIONES
    @Override
    public StatusDTO editFlightByCode(String flightNumber, FlightDTO flightDTO) throws NoFlightFound {
        Flight flightCheck = repo.findByFlightNumber(flightNumber);
        if (flightCheck == null) {
            throw new NoFlightFound();
        }
        Flight flight = new Flight(flightCheck.getId(), flightDTO.getFlightNumber(),
                flightDTO.getName(), flightDTO.getOrigin(), flightDTO.getDestination(), flightDTO.getSeatType(),
                flightDTO.getFlightPrice(), flightDTO.getGoingDate(), flightDTO.getReturnDate());
        repo.save(flight);
        return new StatusDTO("Vuelo modificado correctamente");
    }

    // CONSULTAS/LECTURAS
    @Override
    public List<FlightDTO> getFlights() throws Exception {
        List<Flight> flightList = repo.findAll();
        List<FlightDTO> flightDTOList = flightList.stream().map(flightDTO -> mapper.map(flightDTO, FlightDTO.class)).collect(Collectors.toList());

        Date newDateFrom;
        Date newDateTo;
        if (flightDTOList.size() == 0)
            throw new NoFlightData();
        else {
            for (FlightDTO flight : flightDTOList) {
                newDateFrom = new Date(flight.getGoingDate().getTime() + 3 * (3600 * 1000));
                newDateTo = new Date(flight.getReturnDate().getTime() + 3 * (3600 * 1000));
                flight.setGoingDate(newDateFrom);
                flight.setReturnDate(newDateTo);
            }
        }
        return flightDTOList;
    }

    @Override
    public List<FlightDTO> getListedFlights(String datefrom, String dateto, String origin, String destination) throws Exception {
        Date dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(datefrom);
        Date dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(dateto);

        List<FlightDTO> flightDTOList = new ArrayList<>();
        for (FlightDTO flight : getFlights()) {
            if (dateFrom.after(flight.getGoingDate()) &&
                    dateTo.before(flight.getReturnDate()) &&
                    origin.equalsIgnoreCase(flight.getOrigin())&&
                    destination.equalsIgnoreCase(flight.getDestination()))
                flightDTOList.add(flight);
        }
        if (flightDTOList.size() == 0)
            throw new NoFlightData();

        return flightDTOList;

    }

    // BAJAS
    @Override
    public StatusDTO deleteFlightByFlightNumber(String flightNumber) throws NoFlightFound, UnableToDeleteFlight {
        Flight flight = repo.findByFlightNumber(flightNumber);
        if (flight == null) {
            throw new NoFlightFound();
        }
        List<FlightReservation> flightReservationList = bookingsRepo.findAll();
        for (FlightReservation fr : flightReservationList) {
            if (fr.getFlightReservation().getFlightNumber().equals(flightNumber))
                throw new UnableToDeleteFlight();
        }
        repo.delete(flight);
        return new StatusDTO("Vuelo dado de baja correctamente");
    }
}
