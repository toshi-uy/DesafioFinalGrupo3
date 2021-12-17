package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.NoData;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Flight;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.repositories.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImp implements FlightService {
    ModelMapper mapper = new ModelMapper();
    private FlightRepository repo;

    public FlightServiceImp(FlightRepository repo) {
        this.repo = repo;
    }

    // ALTAS
    @Override
    public StatusDTO addFlight(FlightDTO flight) {
        Flight nuevo = mapper.map(flight, Flight.class);
        repo.save(nuevo);
        return new StatusDTO("Vuelo creado con éxito.");
    }

    // MODIFICACIONES
    @Override
    public StatusDTO editFlightByCode(String flightNumber, FlightDTO flightDTO) {
        Flight flight = new Flight(repo.findByFlightNumber(flightNumber).getId(), flightDTO.getFlightNumber(),
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
            if (flightDTOList.size() == 0)
                throw new NoData("There are no registered flights");

            return flightDTOList;
    }

    @Override
    public List<FlightDTO> getListedFlights(String dateFrom, String dateTo, String origin, String destination) throws Exception {
        Date datefrom = new Date();
        Date dateto = new Date();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            datefrom = formatter.parse(dateFrom);
            dateto = formatter.parse(dateTo);

            List<Flight> flightList = repo.findListedFlights(datefrom, dateto, origin, destination);
            List<FlightDTO> flightDTOList = flightList.stream().map(flightDTO -> mapper.map(flightDTO, FlightDTO.class)).collect(Collectors.toList());
            if (flightDTOList.size() == 0)
                throw new Exception("There are no registered flights for the requested timeframe or destination");

            return flightDTOList;

    }

    // BAJAS
    @Override
    public StatusDTO deleteFlightByFlightNumber(String flightNumber) {
        Flight flight = repo.findByFlightNumber(flightNumber);
        repo.delete(flight);
        return new StatusDTO("Vuelo dado de baja correctamente");
    }
}
