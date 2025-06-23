package roomescape.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;
import roomescape.domain.dto.ReservationRequest;
import roomescape.exception.ReservationNotFoundException;

@RestController
@RequestMapping("/reservations")
public class ReservationApiController {

    private final Map<Long, Reservation> reservations = new ConcurrentHashMap<>();

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(List.copyOf(reservations.values()));
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationRequest dto) {
        Reservation reservation = Reservation.create(dto.getName(), dto.getDate(), dto.getTime());
        reservations.put(reservation.getId(), reservation);
        return ResponseEntity
                .created(URI.create("/reservations/" + reservation.getId()))
                .body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        Reservation removed = reservations.remove(id);
        if (removed == null) {
            throw new ReservationNotFoundException("삭제할 수 있는 예약이 존재하지 않습니다.");
        }
        return ResponseEntity.noContent().build();
    }
}
