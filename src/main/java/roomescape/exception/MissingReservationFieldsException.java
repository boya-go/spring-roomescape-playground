package roomescape.exception;

public class MissingReservationFieldsException extends RuntimeException {
    public MissingReservationFieldsException(String message) {
        super(message);
    }
}
