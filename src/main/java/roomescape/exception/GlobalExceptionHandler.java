package roomescape.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingReservationFieldsException.class)
    public ResponseEntity<String> handleMissingReservationFileds(MissingReservationFieldsException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleReservationNotFound(ReservationNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
