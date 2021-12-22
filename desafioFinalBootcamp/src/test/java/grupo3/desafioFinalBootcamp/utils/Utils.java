package grupo3.desafioFinalBootcamp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import grupo3.desafioFinalBootcamp.models.BookResId;
import grupo3.desafioFinalBootcamp.models.DTOs.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static grupo3.desafioFinalBootcamp.utils.Utils.getFlightReservationDTO;

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

    public static PackageDTO getPackageDTO() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setPackageNumber(999);
        packageDTO.setName("TestPackage");
        packageDTO.setCreation_date(formatter.parse("21/12/2021"));

//        HotelBookingDTO hb = getHotelBookingDTO();
//        FlightReservationDTO fr = getFlightReservationDTO();

        BookResId bookResId = new BookResId();
        packageDTO.setBookingsOrReservations(bookResId);
        return packageDTO;
    }

    public static FlightReservationDTO getFlightReservationDTO() throws ParseException {
        FlightReservationDTO flightReservationDTO = new FlightReservationDTO();
        flightReservationDTO.setUserName("test@user.com");
        flightReservationDTO.setFlightReservation(getReservationDTO());
        return flightReservationDTO;
    }

    private static ReservationDTO getReservationDTO() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setGoingDate(formatter.parse("25/06/2022"));
        reservationDTO.setReturnDate(formatter.parse("30/06/2022"));
        reservationDTO.setOrigin("MVD");
        reservationDTO.setDestination("BUE");
        reservationDTO.setFlightNumber("ABC123");
        reservationDTO.setSeats(2);
        reservationDTO.setSeatType("Economy");
        reservationDTO.setPeople(getPeopleDTO());
        reservationDTO.setPaymentMethod(getPaymentMethodDTO());
        return reservationDTO;
    }

    public static HotelBookingDTO getHotelBookingDTO() throws ParseException {
        HotelBookingDTO hotelBookingDTO = new HotelBookingDTO();
        hotelBookingDTO.setUsername("test@user.com");
        hotelBookingDTO.setBooking(getBookingDTO());
        return hotelBookingDTO;
    }

    private static BookingDTO getBookingDTO() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(999);
        bookingDTO.setDateFrom(formatter.parse("25/06/2022"));
        bookingDTO.setDateTo(formatter.parse("30/06/2022"));
        bookingDTO.setDestination("MVD");
        bookingDTO.setHotelCode("ABC1234");
        bookingDTO.setPeopleAmount(1);
        bookingDTO.setRoomType("Single");
        bookingDTO.setPeople(getPeopleDTO());
        bookingDTO.setPaymentMethod(getPaymentMethodDTO());
        return bookingDTO;
    }

    private static PaymentMethodDTO getPaymentMethodDTO() {
        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CC",
                "4444-3333-2222-1111",
                6);
        return paymentMethod;
    }

    private static List<PersonDTO> getPeopleDTO() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        List<PersonDTO> people = new ArrayList<>();
        people.add(new PersonDTO("12345",
                "test",
                "user",
                formatter.parse("02/03/1999"),
                "test@user.com"));
        return people;
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
