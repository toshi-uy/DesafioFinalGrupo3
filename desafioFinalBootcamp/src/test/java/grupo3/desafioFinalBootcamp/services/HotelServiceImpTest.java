package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.models.HotelBooking;
import grupo3.desafioFinalBootcamp.repositories.HotelBookingRepository;
import grupo3.desafioFinalBootcamp.repositories.HotelRepository;
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
class HotelServiceImpTest {

    @Mock
    private HotelRepository repo;

    @Mock
    private HotelBookingRepository bookingRepo;

    @InjectMocks
    private HotelServiceImp service;

    @Test
    void addHotel() throws DuplicateHotelCode, DuplicateHotelId, ParseException {
        HotelDTO hotel = Utils.getHotelDTO();
        when(repo.findAll()).thenReturn(new ArrayList<>());

        service.addHotel(hotel);

        verify(repo, atLeastOnce()).save(any());
    }

    @Test
    void editHotelByCode() throws NoHotelFound {
        when(repo.findByHotelCode(any())).thenReturn(new Hotel());

        service.editHotelByCode(any(), any());

        verify(repo, atLeastOnce()).save(any());
    }

    @Test
    void getHotels() throws Exception {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel());
        when(repo.findAll()).thenReturn(hotels);

        List<HotelDTO> res = service.getHotels();

        Assertions.assertEquals(1, res.size());
    }

    @Test
    void getListedHotels() throws Exception {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel());
        when(repo.findListedHotels(any(), any(), any())).thenReturn(hotels);

        List<HotelDTO> res = service.getListedHotels("01/01/2022", "02/02/2022", "MVD");

        Assertions.assertEquals(1, res.size());
    }

    @Test
    void deleteHotelByHotelCode() throws UnableToDeleteHotel, NoHotelFound {
        Hotel hotel = new Hotel();
        when(repo.findByHotelCode(any())).thenReturn(hotel);
        when(bookingRepo.findAll()).thenReturn(new ArrayList<>());

        service.deleteHotelByHotelCode(any());

        verify(repo, atLeastOnce()).delete(hotel);
    }
}