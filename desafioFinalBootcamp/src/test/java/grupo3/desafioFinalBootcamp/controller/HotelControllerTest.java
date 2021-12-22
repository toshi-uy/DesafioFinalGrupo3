package grupo3.desafioFinalBootcamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateHotelCode;
import grupo3.desafioFinalBootcamp.exceptions.NoHotelFound;
import grupo3.desafioFinalBootcamp.exceptions.UnableToDeleteHotel;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.repositories.HotelRepository;
import grupo3.desafioFinalBootcamp.services.HotelServiceImp;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {

    @Autowired
    MockMvc mockMVc;

    @Autowired
    HotelRepository repo;
    @Autowired
    HotelServiceImp service;

    @AfterEach
    void deleteTestHotels() throws UnableToDeleteHotel, NoHotelFound {
        Hotel hot = repo.findByHotelCode("ABC123");
        if (hot != null)
            repo.deleteById(hot.getId());
    }

    @Test
    void addHotel() throws Exception {
        HotelDTO hotelDTO = Utils.getHotelDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(hotelDTO);

        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/hotels/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hotel creado con éxito."));
    }

    @Test
    void addHotelDuplicateCode() throws Exception {
        HotelDTO hotelDTO = Utils.getHotelDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(hotelDTO);

        service.addHotel(hotelDTO);

        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/hotels/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DuplicateHotelCode))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("El Codigo del hotel ya se encuentra utilizado."));
    }

    @Test
    void getHotels() throws Exception {

        service.addHotel(Utils.getHotelDTO());

        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/hotels"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    void checkHotelsWithParamsOKTest() throws Exception {

        service.addHotel(Utils.getHotelDTO());

        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "02/01/2022")
                        .param("dateTo", "01/02/2022")
                        .param("destination", "TestPlace")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value("1"));
    }

    @Test
    void getHotels_missingParams() throws Exception {

        service.addHotel(Utils.getHotelDTO());

        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "02/01/2022")
                        .param("dateTo", "01/02/2022")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Por favor ingrese los 3 parametros requeridos"));
    }

    @Test
    void checkHotelsWrongParamsTest() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "25/25/2022")
                        .param("dateTo", "01/23/2022")
                        .param("destination", "XYZ")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("No se encontraron hoteles."));
    }

    @Test
    void editHotelByCode() throws Exception {
        service.addHotel(Utils.getHotelDTO());

        mockMVc.perform(MockMvcRequestBuilders.put("/api/v1/hotels/edit?hotelCode=ABC123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPost()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hotel modificado correctamente"));
    }

    @Test
    void editHotelNoHotelFound() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.put("/api/v1/hotels/edit?hotelCode=XYZ123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPostDuplicateId()))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoHotelFound))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("No se encontraron hoteles con ese código."));
    }

    @Test
    void deleteHotelWrongCode() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.delete("/api/v1/hotels/delete?hotelCode=XYZ123"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("No se encontraron hoteles con ese código."));
    }

    @Test
    void deleteHotelOK() throws Exception {

        service.addHotel(Utils.getHotelDTO());

        mockMVc.perform(MockMvcRequestBuilders.delete("/api/v1/hotels/delete?hotelCode=ABC123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hotel dado de baja correctamente."));
    }
}