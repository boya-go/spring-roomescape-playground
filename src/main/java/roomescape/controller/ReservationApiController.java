package roomescape.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;
import roomescape.domain.Reservations;
import roomescape.domain.dto.ReservationRequest;
import roomescape.exception.ReservationNotFoundException;

@RestController
@RequestMapping("/reservations")
public class ReservationApiController {

    private final Reservations reservations = new Reservations();

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservations.findAll());
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationRequest dto) {
        Reservation reservation = Reservation.create(dto.getName(), dto.getDate(), dto.getTime());
        reservations.save(reservation);
        return ResponseEntity
                .created(URI.create("/reservations/" + reservation.getId()))
                .body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservations.delete(id);
        return ResponseEntity.noContent().build();
    }
}
