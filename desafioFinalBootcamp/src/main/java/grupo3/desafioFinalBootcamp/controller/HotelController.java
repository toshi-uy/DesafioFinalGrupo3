package grupo3.desafioFinalBootcamp.controller;

import grupo3.desafioFinalBootcamp.exceptions.*;
import grupo3.desafioFinalBootcamp.models.DTOs.HotelDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    // ALTAS
    @PostMapping("/new")
    public ResponseEntity<StatusDTO> addHotel(@RequestBody HotelDTO hotel) throws DuplicateHotelCode, DuplicateHotelId {
        return new ResponseEntity<>(service.addHotel(hotel), HttpStatus.OK);
    }

    // BAJAS
    @DeleteMapping("/delete")
    public ResponseEntity<StatusDTO> deleteHotel(@RequestParam String hotelCode) throws UnableToDeleteHotel, NoHotelFound {
        return new ResponseEntity<>(service.deleteHotelByHotelCode(hotelCode), HttpStatus.OK);
    }

    // CONSULTAS/LECTURAS
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getHotels(@RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo, @RequestParam(required = false) String destination) throws Exception, NoHotelData {

        if (dateFrom == null || dateTo == null || destination == null)
            if (dateFrom != null || dateTo != null || destination != null)
                throw new Exception("Please enter the 3 requested parameter: Date From, Date To and Destination");
            else
                return new ResponseEntity<>(service.getHotels(), HttpStatus.OK);
        return new ResponseEntity<>(service.getListedHotels(dateFrom, dateTo, destination), HttpStatus.OK);
    }

    // MODIFICACIONES
    @PutMapping("/edit")
    public ResponseEntity<StatusDTO> editHotelByCode(@RequestParam String hotelCode, @RequestBody HotelDTO hotelDTO) throws NoHotelFound {
        return new ResponseEntity<>(service.editHotelByCode(hotelCode, hotelDTO), HttpStatus.OK);
    }
}
