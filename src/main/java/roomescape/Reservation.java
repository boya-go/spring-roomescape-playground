package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;

    public Reservation(Long reservationId, String name, LocalDate reservationDate, LocalTime reservationTime) {
        this.id = reservationId;
        this.name = name;
        this.date = reservationDate;
        this.time = reservationTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public static Reservation toEntity(Reservation reservation, Long id) {
        return new Reservation(id, reservation.getName(), reservation.getDate(), reservation.getTime());
    }
}
