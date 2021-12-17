package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;

import java.util.List;

public interface HotelService {

    StatusDTO addHotel(HotelDTO hotel);

    StatusDTO deleteHotelByHotelCode(String hotelCode);

    List<HotelDTO> getHotels();

    List<HotelDTO> getListedHotels(String dateFrom, String dateTo, String destination);

    StatusDTO editHotelByCode(String hotelCode);
}
