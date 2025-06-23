package roomescape.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;
import roomescape.exception.MissingReservationFieldsException;

public class Reservation {

    private static final AtomicLong index = new AtomicLong(1);

    private final Long id;
    private final String name;
    private final LocalDate date;
    private final LocalTime time;

    public Reservation(Long id, String name, LocalDate date, LocalTime time) {
        vaildate(name, date, time);
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    private void vaildate(String name, LocalDate date, LocalTime time){
        if (name == null || name.isEmpty() || date == null || time == null) {
            throw new MissingReservationFieldsException("모든 필드를 입력하셔야 합니다.");
        }
    }

    public static Reservation create(String name, LocalDate date, LocalTime time) {
        long id = index.getAndIncrement();
        return new Reservation(id, name, date, time);
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
}
