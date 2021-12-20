package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.DuplicateHotelCode;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateHotelId;
import grupo3.desafioFinalBootcamp.exceptions.NoHotelFound;
import grupo3.desafioFinalBootcamp.exceptions.UnableToDelete;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;

import java.util.List;

public interface HotelService {

    StatusDTO addHotel(HotelDTO hotel) throws DuplicateHotelId, DuplicateHotelCode;

    StatusDTO deleteHotelByHotelCode(String hotelCode) throws UnableToDelete, NoHotelFound;

    List<HotelDTO> getHotels() throws Exception;

    List<HotelDTO> getListedHotels(String dateFrom, String dateTo, String destination) throws Exception;

    StatusDTO editHotelByCode(String hotelCode, HotelDTO hotelDTO) throws NoHotelFound;
}
