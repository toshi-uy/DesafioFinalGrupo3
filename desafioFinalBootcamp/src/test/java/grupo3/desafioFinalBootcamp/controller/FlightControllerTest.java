package grupo3.desafioFinalBootcamp.controller;

import grupo3.desafioFinalBootcamp.exceptions.DuplicateFlightNumber;
import grupo3.desafioFinalBootcamp.exceptions.NoFlightFound;
import grupo3.desafioFinalBootcamp.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FlightControllerTest {

    @Autowired
    MockMvc mockMVc;

    @Test
    void addFlight() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/Flights/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPost()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Flight creado con éxito."));
    }

    @Test
    void addFlightDuplicateCode() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/flights/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPost()))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DuplicateFlightNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("El Codigo del Flight ya se encuentra utilizado."));
    }

    @Test
    void addFlightDuplicateId() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/flights/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPostDuplicateId()))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DuplicateFlightNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("El Id del Flight ya se encuentra utilizado."));
    }

    @Test
    void getFlights() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/flights"))
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.flights[0].flightCode").value("CH-0002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.flights").isNotEmpty());
    }

    @Test
    void checkFlightsWithParamsOKTest() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "12/02/2022")
                        .param("dateTo", "18/02/2022")
                        .param("destination", "Puerto Iguazú")
                )
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.flights.length()").value("1"));
    }

    @Test
    void checkFlightsWrongParamsTest() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "25/25/2022")
                        .param("dateTo", "01/23/2022")
                        .param("destination", "Bogotá")
                )
                .andDo(print()).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("ERROR MESSAGE."));
    }

    @Test
    void editFlightByCode() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.put("/api/v1/flights/edit", "?flightCode=CH-0002")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPost()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Flight modificado correctamente."));
    }

    @Test
    void editFlightNoFlightFound() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.put("/api/v1/flights/edit", "?flightCode=WRONG_CODE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPostDuplicateId()))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFlightFound))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("No se encontraron Flights con ese código."));
    }

    @Test
    void deleteFlightWrongCode() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.delete("/api/v1/flights/edit", "?flightCode=WRONG"))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFlightFound))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("No se encontraron Flights con ese código."));
    }

    @Test
    void deleteFlightOK() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.delete("/api/v1/flights/edit", "?flightCode=CH-0002"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Flight dado de baja correctamente."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.flights.length()").isEmpty());
    }
}