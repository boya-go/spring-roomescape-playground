package roomescape.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingReservationFiledsException.class)
    public ResponseEntity<String> handleMissingReservationFileds(MissingReservationFiledsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleReservationNotFound(ReservationNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
