package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.models.HotelBooking;
import grupo3.desafioFinalBootcamp.repositories.HotelBookingRepository;
import grupo3.desafioFinalBootcamp.repositories.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public StatusDTO addHotel(HotelDTO hotel) throws DuplicateHotelId, DuplicateHotelCode {
        Hotel nuevo = mapper.map(hotel, Hotel.class);
        if (repo.findAll().size() == 0) {
            repo.save(nuevo);
            return new StatusDTO("Hotel creado con éxito.");
        }
        Hotel check = repo.findByHotelCode(hotel.getHotelCode());
        if (check != null) {
            throw new DuplicateHotelCode();
        }
        check = repo.findHotelById(hotel.getId());
        if (check != null) {
            throw new DuplicateHotelId();
        }
        repo.save(nuevo);
        return new StatusDTO("Hotel creado con éxito.");
    }

    // MODIFICACIONES
    public StatusDTO editHotelByCode(String hotelCode, HotelDTO hotelDTO) throws NoHotelFound {
        Hotel hotelCheck = repo.findByHotelCode(hotelCode);
        if (hotelCheck == null) {
            throw new NoHotelFound();
        }
        Hotel hotel = new Hotel(hotelCheck.getId(), hotelDTO.getHotelCode(), hotelDTO.getName(), hotelDTO.getPlace(), hotelDTO.getRoomType(), hotelDTO.getRoomPrice(), hotelDTO.getDisponibilityDateFrom(), hotelDTO.getDisponibilityDateTo(), hotelDTO.getIsBooking());
        repo.save(hotel);
        return new StatusDTO("Hotel modificado correctamente");
    }

    // CONSULTAS/LECTURAS
    public List<HotelDTO> getHotels() throws Exception {

        List<Hotel> hotelList = repo.findAll();
        List<HotelDTO> hotelDTOList = hotelList.stream().map(hotelDTO -> mapper.map(hotelDTO, HotelDTO.class)).collect(Collectors.toList());

        Date newDateFrom;
        Date newDateTo;

        if (hotelDTOList.size() == 0)
            throw new NoHotelData();
        else
            for (HotelDTO hotel : hotelDTOList) {
                newDateFrom = new Date(hotel.getDisponibilityDateFrom().getTime() + 3 * (3600 * 1000));
                newDateTo = new Date(hotel.getDisponibilityDateTo().getTime() + 3 * (3600 * 1000));
                hotel.setDisponibilityDateFrom(newDateFrom);
                hotel.setDisponibilityDateTo(newDateTo);
            }

        return hotelDTOList;
    }

    public List<HotelDTO> getListedHotels(String datefrom, String dateto, String destination) throws Exception {

        Date dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(datefrom);
        Date dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(dateto);

        List<HotelDTO> filteredList = new ArrayList<>();
        for (HotelDTO hotel : getHotels()) {
            if (dateFrom.after(hotel.getDisponibilityDateFrom()) && dateTo.before(hotel.getDisponibilityDateTo()) && destination.equalsIgnoreCase(hotel.getPlace()))
                filteredList.add(hotel);
        }

        if (filteredList.size() == 0)
            throw new NoHotelData();

        return filteredList;
    }

    // BAJAS
    public StatusDTO deleteHotelByHotelCode(String hotelCode) throws UnableToDeleteHotel, NoHotelFound {
        Hotel hotel = repo.findByHotelCode(hotelCode);
        if (hotel == null) {
            throw new NoHotelFound();
        }
        List<HotelBooking> hotelBookingList = bookingRepo.findAll();
        for (HotelBooking hb : hotelBookingList) {
//            if (hb.getHotel().getHotelCode().equals(hotelCode))
                throw new UnableToDeleteHotel();
        }
        repo.delete(hotel);
        return new StatusDTO("Hotel dado de baja correctamente.");
    }

}
