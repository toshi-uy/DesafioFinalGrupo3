package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.repositories.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class HotelServiceImp implements HotelService {

    final HotelRepository repo;
    ModelMapper mapper = new ModelMapper();

    public HotelServiceImp(HotelRepository repo) {
        this.repo = repo;
    }

    // ALTAS
    public StatusDTO addHotel(HotelDTO hotel) {
        Hotel nuevo = mapper.map(hotel, Hotel.class);
        repo.save(nuevo);
        return new StatusDTO("Hotel creado con éxito.");
    }

    // MODIFICACIONES
    public StatusDTO editHotelByCode(String hotelCode) {
        repo.save(repo.findByHotelCode(hotelCode));
        return new StatusDTO("Hotel modificado correctamente");
    }

    // CONSULTAS/LECTURAS
    public List<HotelDTO> getHotels() {

        List<Hotel> hotelList = repo.findAll();
        return hotelList.stream().map(hotelDTO -> mapper.map(hotelDTO, HotelDTO.class)).collect(Collectors.toList());

    }

    public List<HotelDTO> getListedHotels(String dateFrom, String dateTo, String destination) {
        Date datefrom = new Date();
        Date dateto = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            datefrom = formatter.parse(dateFrom);
            dateto = formatter.parse(dateTo);

        } catch (Exception ex) {

        }
        List<Hotel> hotelList = repo.findListedHotels(datefrom, dateto, destination);

        return hotelList.stream().map(hotelDTO -> mapper.map(hotelDTO, HotelDTO.class)).collect(Collectors.toList());
    }

    // BAJAS
    public StatusDTO deleteHotelByHotelCode(String hotelCode) {
        repo.deleteHotelByHotelCode(hotelCode);
        return new StatusDTO("Hotel dado de baja correctamente");
    }

}
