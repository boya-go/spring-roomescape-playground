package roomescape.domain;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import roomescape.exception.ReservationNotFoundException;

public class Reservations {

    private final Map<Long, Reservation> reservations = new ConcurrentHashMap<>();

    public List<Reservation> findAll() {
        return List.copyOf(reservations.values());
    }

    public Reservation save(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    public void delete(Long id) {
        if (!reservations.containsKey(id)) {
            throw new ReservationNotFoundException("삭제할 수 있는 예약이 존재하지 않습니다.");
        }
        reservations.remove(id);
    }
}
