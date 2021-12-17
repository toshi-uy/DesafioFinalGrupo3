package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.DuplicateHotelCode;
import grupo3.desafioFinalBootcamp.exceptions.DuplicateHotelId;
import grupo3.desafioFinalBootcamp.exceptions.NoData;
import grupo3.desafioFinalBootcamp.exceptions.UnableToDelete;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.models.HotelBooking;
import grupo3.desafioFinalBootcamp.repositories.HotelBookingRepository;
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
    final HotelBookingRepository bookingRepo;
    ModelMapper mapper = new ModelMapper();

    public HotelServiceImp(HotelRepository repo, HotelBookingRepository bookingRepo) {
        this.repo = repo;
        this.bookingRepo = bookingRepo;
    }

    // ALTAS
    public StatusDTO addHotel(HotelDTO hotel) throws DuplicateHotelId, DuplicateHotelCode{
        Hotel nuevo = mapper.map(hotel, Hotel.class);
        if(repo.findAll().size() == 0){
            repo.save(nuevo);
            return new StatusDTO("Hotel creado con éxito.");
        }
        for (Hotel h: repo.findAll()) {
            if(h.getId() == hotel.getId())
                throw new DuplicateHotelId();
            if(h.getHotelCode() == hotel.getHotelCode())
                throw new DuplicateHotelCode();
        }
        repo.save(nuevo);
        return new StatusDTO("Hotel creado con éxito.");
    }

    // MODIFICACIONES
    public StatusDTO editHotelByCode(String hotelCode, HotelDTO hotelDTO) {
        Hotel hotel = new Hotel(repo.findByHotelCode(hotelCode).getId(), hotelDTO.getHotelCode(), hotelDTO.getName(), hotelDTO.getPlace(), hotelDTO.getRoomType(), hotelDTO.getRoomPrice(), hotelDTO.getDisponibilityDateFrom(), hotelDTO.getDisponibilityDateTo(), hotelDTO.getIsBooking());
        repo.save(hotel);
        return new StatusDTO("Hotel modificado correctamente");
    }

    // CONSULTAS/LECTURAS
    public List<HotelDTO> getHotels() throws Exception {

            List<Hotel> hotelList = repo.findAll();
            List<HotelDTO> hotelDTOList = hotelList.stream().map(hotelDTO -> mapper.map(hotelDTO, HotelDTO.class)).collect(Collectors.toList());

            if (hotelDTOList.size() == 0)
                throw new Exception("There are no registered hotels");

            return hotelDTOList;
    }

    public List<HotelDTO> getListedHotels(String dateFrom, String dateTo, String destination) throws Exception {

        Date datefrom = new Date();
        Date dateto = new Date();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            datefrom = formatter.parse(dateFrom);
            dateto = formatter.parse(dateTo);

        List<Hotel> hotelList = repo.findListedHotels(datefrom, dateto, destination);

        List<HotelDTO>  hotelDTOList = hotelList.stream().map(hotelDTO -> mapper.map(hotelDTO, HotelDTO.class)).collect(Collectors.toList());
        if(hotelDTOList.size()==0)
            throw new NoData("There are no registered flights for the requested timeframe, origin or destination");
    return hotelDTOList;
    }

    // BAJAS
    public StatusDTO deleteHotelByHotelCode(String hotelCode) {
        Hotel hotel = repo.findByHotelCode(hotelCode);
        List<HotelBooking> hotelBookingList = bookingRepo.findAll();
        for (HotelBooking hb:hotelBookingList) {
            if(hb.getHotel().getHotelCode().equals(hotelCode))
                //"No se puede dar de baja el hotel. Existe una reserva activa que lo contiene."
                throw new UnableToDelete();
        }
        repo.delete(hotel);
        return new StatusDTO("Hotel dado de baja correctamente");
    }

}
