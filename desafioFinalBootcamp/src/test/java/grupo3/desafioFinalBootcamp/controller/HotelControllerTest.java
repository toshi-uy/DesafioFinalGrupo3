package grupo3.desafioFinalBootcamp.controller;

import grupo3.desafioFinalBootcamp.exceptions.DuplicateHotelCode;
import grupo3.desafioFinalBootcamp.exceptions.NoHotelFound;
import grupo3.desafioFinalBootcamp.utils.Utils;
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

    @Test
    void addHotel() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/hotels/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPost()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hotel creado con éxito."));
    }

    @Test
    void addHotelDuplicateCode() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/hotels/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPost()))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DuplicateHotelCode))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("El Codigo del hotel ya se encuentra utilizado."));
    }

    @Test
    void addHotelDuplicateId() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.post("/api/v1/hotels/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPostDuplicateId()))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DuplicateHotelCode))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("El Id del hotel ya se encuentra utilizado."));
    }

    @Test
    void getHotels() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/hotels"))
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotels[0].hotelCode").value("CH-0002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotels").isNotEmpty());
    }

    @Test
    void checkHotelsWithParamsOKTest() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "12/02/2022")
                        .param("dateTo", "18/02/2022")
                        .param("destination", "Puerto Iguazú")
                )
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotels.length()").value("1"));
    }

    @Test
    void checkHotelsWrongParamsTest() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "25/25/2022")
                        .param("dateTo", "01/23/2022")
                        .param("destination", "Bogotá")
                )
                .andDo(print()).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("ERROR MESSAGE."));
    }

    @Test
    void editHotelByCode() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.put("/api/v1/hotels/edit", "?hotelCode=CH-0002")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPost()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hotel modificado correctamente."));
    }

    @Test
    void editHotelNoHotelFound() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.put("/api/v1/hotels/edit", "?hotelCode=WRONG_CODE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getJsonPostDuplicateId()))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoHotelFound))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("No se encontraron hoteles con ese código."));
    }

    @Test
    void deleteHotelWrongCode() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.delete("/api/v1/hotels/edit", "?hotelCode=WRONG"))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoHotelFound))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description")
                        .value("No se encontraron hoteles con ese código."));
    }

    @Test
    void deleteHotelOK() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.delete("/api/v1/hotels/delete", "?hotelCode=CH-0002"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hotel dado de baja correctamente."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotels.length()").isEmpty());
    }
}