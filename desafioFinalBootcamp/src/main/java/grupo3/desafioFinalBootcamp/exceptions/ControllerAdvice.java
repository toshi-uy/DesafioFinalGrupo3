package grupo3.desafioFinalBootcamp.exceptions;

import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UnableToDelete.class)
    public ResponseEntity<StatusDTO> unableToDelete(UnableToDelete ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateHotelId.class)
    public ResponseEntity<StatusDTO> duplicateId(DuplicateHotelId ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateHotelCode.class)
    public ResponseEntity<StatusDTO> duplicateId(DuplicateHotelCode ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateFlightNumber.class)
    public ResponseEntity<StatusDTO> duplicateId(DuplicateFlightNumber ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateFlightId.class)
    public ResponseEntity<StatusDTO> duplicateId(DuplicateFlightId ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHotelFound.class)
    public ResponseEntity<StatusDTO> duplicateId(NoHotelFound ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoFlightFound.class)
    public ResponseEntity<StatusDTO> duplicateId(NoFlightFound ex) {
        StatusDTO error = new StatusDTO();
        error.setMessage(ex.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
