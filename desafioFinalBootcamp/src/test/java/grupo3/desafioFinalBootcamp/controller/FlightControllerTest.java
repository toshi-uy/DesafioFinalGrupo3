package grupo3.desafioFinalBootcamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateFlightNumber;
import grupo3.desafioFinalBootcamp.exceptions.NoFlightFound;
import grupo3.desafioFinalBootcamp.exceptions.NoHotelFound;
import grupo3.desafioFinalBootcamp.exceptions.UnableToDeleteHotel;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.Flight;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.repositories.FlightRepository;
import grupo3.desafioFinalBootcamp.services.FlightServiceImp;
import grupo3.desafioFinalBootcamp.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {

    @Autowired
    MockMvc mockMVc;

    @Autowired
    FlightRepository repo;
    @Autowired
    FlightServiceImp service;

    @AfterEach
    void deleteTestHotels() throws Exception{
        Flight flight = repo.findByFlightNumber("ABC123");
        if (flight != null)
            repo.deleteById(flight.getId());
    }

    @Test
    void addFlight() throws Exception {
        FlightDTO flightDTO = Utils.getFlightDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(flightDTO);

        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/flights/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Vuelo creado con éxito."));
    }

    @Test
    void addFlightDuplicateCode() throws Exception {
        FlightDTO flightDTO = Utils.getFlightDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(flightDTO);

        service.addFlight(flightDTO);

        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/flights/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("El número de vuelo ya se encuentra utilizado."));
    }

    @Test
    void getFlights() throws Exception {

        service.addFlight(Utils.getFlightDTO());

        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/flights"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    void checkFlightsWithParamsOKTest() throws Exception {

        service.addFlight(Utils.getFlightDTO());

        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "02/01/2022")
                        .param("dateTo", "03/01/2022")
                        .param("origin", "MVD")
                        .param("destination", "BUE")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value("1"));
    }

    @Test
    void getFlights_missingParams() throws Exception {

        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "02/01/2022")
                        .param("dateTo", "01/02/2022")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Por favor ingrese los todos parametros requeridos"));
    }

    @Test
    void checkFlightsWrongParamsTest() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "01/01/2022")
                        .param("dateTo", "02/01/2022")
                        .param("origin", "ZZZ")
                        .param("destination", "YYY")
                )
                .andDo(print()).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("No se encontraron vuelos."));
    }

    @Test
    void editFlightByCode() throws Exception {
        FlightDTO flightDTO = Utils.getFlightDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(flightDTO);

        service.addFlight(Utils.getFlightDTO());

        mockMVc.perform(MockMvcRequestBuilders.put("/api/v1/flights/edit?flightCode=ABC123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Vuelo modificado correctamente"));
    }

    @Test
    void editFlightNoFlightFound() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.put("/api/v1/flights/edit?flightCode=WRONG_CODE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPostDuplicateId()))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("No se encontraron vuelos con ese código."));
    }

    @Test
    void deleteFlightWrongCode() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.delete("/api/v1/flights/delete?flightNumber=WRONG"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("No se encontraron vuelos con ese código."));
    }

    @Test
    void deleteFlightOK() throws Exception {

        service.addFlight(Utils.getFlightDTO());

        mockMVc.perform(MockMvcRequestBuilders.delete("/api/v1/flights/delete?flightNumber=ABC123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Vuelo dado de baja correctamente"));
    }
}