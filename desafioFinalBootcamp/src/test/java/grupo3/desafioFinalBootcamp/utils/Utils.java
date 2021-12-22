package grupo3.desafioFinalBootcamp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import grupo3.desafioFinalBootcamp.models.DTOs.FlightDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static HotelDTO getHotelDTO() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotelCode("ABC123");
        hotelDTO.setName("TestName");
        hotelDTO.setPlace("TestPlace");
        hotelDTO.setRoomType("TestRoomType");
        hotelDTO.setRoomPrice(100);
        hotelDTO.setDisponibilityDateFrom(formatter.parse("01/01/2022"));
        hotelDTO.setDisponibilityDateTo(formatter.parse("02/02/2022"));
        hotelDTO.setIsBooking(false);
        return hotelDTO;
    }

    public static FlightDTO getFlightDTO() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        FlightDTO flightDTO = new FlightDTO(
                "ABC123",
                "TestName",
                "MVD",
                "BUE",
                "TestSeat",
                1000,
                formatter.parse("01/01/2022"),
                formatter.parse("10/01/2022")
                );
        return flightDTO;
    }


    public static String getJsonPost() throws JsonProcessingException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        HotelDTO payloadDTO = new HotelDTO("CH-0002", "Cataratas Hotel", "Puerto Iguazú",
                "Doble", 6300, formatter.parse("10/02/2022"),
                formatter.parse("20/03/2022"), false);
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        return writer.writeValueAsString(payloadDTO);
    }
    public static String getJsonPostDuplicateId() throws JsonProcessingException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        HotelDTO payloadDTO = new HotelDTO(1, "CH-0002", "Cataratas Hotel", "Puerto Iguazú",
                "Doble", 6300, formatter.parse("10/02/2022"),
                formatter.parse("20/03/2022"), false);
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        return writer.writeValueAsString(payloadDTO);
    }

    public static String getJsonPut() throws JsonProcessingException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        HotelDTO payloadDTO = new HotelDTO("CH-0002", "Cataratas Hotel Renovado", "Puerto Iguazú",
                "Triple", 8900, formatter.parse("10/02/2022"),
                formatter.parse("20/03/2022"), false);
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        return writer.writeValueAsString(payloadDTO);
    }
}
