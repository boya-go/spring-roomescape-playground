package roomescape.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.exception.MissingReservationFiledsException;

public record Reservation(Long id, String name, LocalDate date, LocalTime time) {

    public Reservation {
        if (name == null || name.isEmpty() || date == null || time == null) {
            throw new MissingReservationFiledsException("모든 필드를 입력하셔야 합니다.");
        }
    }

    public static Reservation toEntity(Reservation reservation, Long id) {
        return new Reservation(id, reservation.name, reservation.date, reservation.time);
    }
}
