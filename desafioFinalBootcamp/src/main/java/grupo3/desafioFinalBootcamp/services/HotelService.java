package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;

import java.util.List;

public interface HotelService {

    StatusDTO addHotel(HotelDTO hotel);

    StatusDTO deleteHotelByHotelCode(String hotelCode);

    List<HotelDTO> getHotels() throws Exception;

    List<HotelDTO> getListedHotels(String dateFrom, String dateTo, String destination) throws Exception;

    StatusDTO editHotelByCode(String hotelCode, HotelDTO hotelDTO);
}
