package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.models.DTOs.FlightDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Flight;
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
    public StatusDTO editFlightByCode(String flightNumber) {
        repo.save(repo.findByFlightNumber(flightNumber));
        return new StatusDTO("Vuelo modificado correctamente");
    }

    // CONSULTAS/LECTURAS
    @Override
    public List<FlightDTO> getFlights() {

        List<Flight> flightList = repo.findAll();
        return flightList.stream().map(flightDTO -> mapper.map(flightDTO, FlightDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<FlightDTO> getListedFlights(String dateFrom, String dateTo, String origin, String destination) {
        Date datefrom = new Date();
        Date dateto = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            datefrom = formatter.parse(dateFrom);
            dateto = formatter.parse(dateTo);

        } catch (Exception ex) {

        }
        List<Flight> flightList = repo.findListedFlights(datefrom, dateto, origin, destination);
        return flightList.stream().map(flightDTO -> mapper.map(flightDTO, FlightDTO.class)).collect(Collectors.toList());
    }

    // BAJAS
    @Override
    public StatusDTO deleteFlightByFlightNumber(String flightNumber) {
        repo.deleteFlightByFlightNumber(flightNumber);
        return new StatusDTO("Vuelo dado de baja correctamente");
    }
}
