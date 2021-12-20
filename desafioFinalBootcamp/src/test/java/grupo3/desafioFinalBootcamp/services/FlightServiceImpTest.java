package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.DuplicateFlightId;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateFlightNumber;
import grupo3.desafioFinalBootcamp.exceptions.NoFlightFound;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightDTO;
import grupo3.desafioFinalBootcamp.models.Flight;
import grupo3.desafioFinalBootcamp.repositories.FlightRepository;
import grupo3.desafioFinalBootcamp.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceImpTest {

    @Mock
    FlightRepository repo;

    @InjectMocks
    FlightServiceImp service;

    @Test
    void addFlight() throws DuplicateFlightNumber, DuplicateFlightId, ParseException {
        when(repo.findAll()).thenReturn(new ArrayList<>());

        service.addFlight(Utils.getFlightDTO());

        verify(repo, atLeastOnce()).save(any());
    }

    @Test
    void editFlightByCode() throws NoFlightFound {
        when(repo.findByFlightNumber(any())).thenReturn(new Flight());

        service.editFlightByCode(eq("test"), any());

        verify(repo, atLeastOnce()).save(any());
    }

    @Test
    void getFlights() throws Exception {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        when(repo.findAll()).thenReturn(flights);

        List<FlightDTO> res = service.getFlights();

        Assertions.assertEquals(1, res.size());
    }

    @Test
    void getListedFlights() throws Exception {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight());
        when(repo.findListedFlights(any(), any(), any(), any())).thenReturn(flights);

        List<FlightDTO> res = service.getListedFlights("01/01/2022", "02/02/2022", "MVD", "BUE");

        Assertions.assertEquals(1, res.size());
    }

    @Test
    void deleteFlightByFlightNumber() {

    }
}