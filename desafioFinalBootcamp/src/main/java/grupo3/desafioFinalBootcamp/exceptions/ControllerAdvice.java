package grupo3.desafioFinalBootcamp.exceptions;

import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UnableToDeleteHotel.class)
    public ResponseEntity<StatusDTO> unableToDelete(UnableToDeleteHotel ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnableToDeleteFlight.class)
    public ResponseEntity<StatusDTO> unableToDelete(UnableToDeleteFlight ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateHotelId.class)
    public ResponseEntity<StatusDTO> duplicateId(DuplicateHotelId ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateHotelCode.class)
    public ResponseEntity<StatusDTO> duplicateId(DuplicateHotelCode ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateFlightNumber.class)
    public ResponseEntity<StatusDTO> duplicateId(DuplicateFlightNumber ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateFlightId.class)
    public ResponseEntity<StatusDTO> duplicateId(DuplicateFlightId ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHotelFound.class)
    public ResponseEntity<StatusDTO> noHotel(NoHotelFound ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoFlightFound.class)
    public ResponseEntity<StatusDTO> NoFlight(NoFlightFound ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoBookingFound.class)
    public ResponseEntity<StatusDTO> NoBooking(NoBookingFound ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoReservationFound.class)
    public ResponseEntity<StatusDTO> NoReservation(NoReservationFound ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PackageNotFound.class)
    public ResponseEntity<StatusDTO> NoPackage(PackageNotFound ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHotelData.class)
    public ResponseEntity<StatusDTO> noHotelData(NoHotelData ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateBooking.class)
    public ResponseEntity<StatusDTO> duplicateBooking(DuplicateBooking ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateReservation.class)
    public ResponseEntity<StatusDTO> duplicateReservation(DuplicateReservation ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoFlightData.class)
    public ResponseEntity<StatusDTO> noFlightData(NoFlightData ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPackages.class)
    public ResponseEntity<StatusDTO> noPackages(NoPackages ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
